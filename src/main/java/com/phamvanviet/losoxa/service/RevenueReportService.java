package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.LineItem;
import com.phamvanviet.losoxa.entity.Order;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.mapper.Converter;
import com.phamvanviet.losoxa.model.dto.ExcelReportDTO;
import com.phamvanviet.losoxa.model.dto.ReportDTO;
import com.phamvanviet.losoxa.model.request.report.ReportRequest;
import com.phamvanviet.losoxa.repository.LineItemRepository;
import com.phamvanviet.losoxa.repository.OrderRepository;
import com.phamvanviet.losoxa.repository.ProductRepository;
import com.phamvanviet.losoxa.repository.ReportRepository;
import com.phamvanviet.losoxa.util.DateUtils;
import com.phamvanviet.losoxa.util.ExcelExport;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RevenueReportService {

    @Autowired
    private ExcelExport excelExport;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Converter converter;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LineItemRepository lineItemRepository;

    public String exportAll(ReportRequest dto) throws IOException, ParseException {
        String starDate = DateUtils.getDate(dto.getStartDate()) + "/" + DateUtils.getMonth(dto.getStartDate()) + "/" + DateUtils.getYear(dto.getStartDate());
        String endDate = DateUtils.getDate(dto.getEndDate()) + "/" + DateUtils.getMonth(dto.getEndDate()) + "/" + DateUtils.getYear(dto.getEndDate());
        List<ExcelReportDTO> list = getProductPopular(dto);
        Collections.sort(list, Comparator.comparingInt(ExcelReportDTO::getQuantitySold).reversed());
        String nameFile = UUID.randomUUID().toString() + ".xlsx";
        String name = "Bao-cao-doanh-so-ban-hang" + nameFile;
        StringBuilder path = new StringBuilder("D://Losoxa//report//" + "/Revenue/" + name);
        XSSFCell cell = null;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Đề xuất");

        excelExport.setTitle(cell, sheet, "BÁO CÁO DOANH SỐ BÁN HÀNG", new CellRangeAddress(4, 4, 0, 4), 4, 0);
        excelExport.setTitle(cell, sheet, "  Từ ngày " + starDate + " Đến ngày " + endDate, new CellRangeAddress(8, 8, 0, 4), 8, 0);
        Integer rowIndex = 10;
        excelExport.setContentHeader(sheet, rowIndex, cell, getNameColumnsPlan());
        setContentTables(list, sheet, rowIndex);
        excelExport.createOutputFile(workbook, path.toString());
        if (list.size() > 0) {
            return path.toString();
        } else {
            return "false";
        }

    }

    private List<ExcelReportDTO> getProductPopular(ReportRequest dto) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(formatter.format(dto.getStartDate()));
        Date endDate = formatter.parse(formatter.format(dto.getEndDate()));


        List<ExcelReportDTO> excelPlanReportDTOS = new ArrayList<>();

        List<Tuple> reportRequests = productRepository.findAllByOrderAndPredictMonth(startDate, endDate);

        int quantitySold;
        Long totalPrice;
        Long realRevenue;
        int point;
        Long cancelOrder;
        for (Tuple productResponse : reportRequests) {
            ExcelReportDTO excelReportDTO = new ExcelReportDTO();
//                if(dto.getPoscodeId() == null)
//                    excelReportDTO.setNameUnit("Tổng công ty");
//                else
            if (productResponse.get(0).toString() != null)
                excelReportDTO.setProductName(productResponse.get(0).toString());
            else excelReportDTO.setProductName("");

            if (productResponse.get(1).toString() != null)
                excelReportDTO.setUnitPrice(Long.parseLong(productResponse.get(1).toString()));
            else excelReportDTO.setUnitPrice(0L);

            if (productResponse.get(2).toString() != null)
                excelReportDTO.setCountOrder(Integer.parseInt(productResponse.get(2).toString()));
            else excelReportDTO.setCountOrder(0);

            quantitySold = 0;
            totalPrice = 0L;
            realRevenue = 0L;
            point = 0;
            cancelOrder = 0L;
            List<LineItem> lineItems = lineItemRepository.findLineItemByProductIdAndTime(Long.parseLong(productResponse.get(3).toString()), startDate, endDate);

            for (LineItem lineItem : lineItems) {
                totalPrice += lineItem.getUnitPrice() * lineItem.getQuantity();
                Order order = orderRepository.findOrderById(lineItem.getOrder().getId());
                if(order.getStatus().equalsIgnoreCase("Đã giao")){
                    quantitySold += lineItem.getQuantity();
                    if(order.getPoint() != null)
                        point += order.getPoint();
                    realRevenue += lineItem.getUnitPrice() * lineItem.getQuantity();
                }
                if(order.getStatus().equalsIgnoreCase("Đã hủy")){
                    cancelOrder += lineItem.getUnitPrice() * lineItem.getQuantity();
                }
            }

            excelReportDTO.setQuantitySold(quantitySold);
            excelReportDTO.setTotalPrice(totalPrice);
            excelReportDTO.setPoint(point*(-1000));
            excelReportDTO.setCancelOrder(cancelOrder*(-1));
            excelReportDTO.setRealRevenue(realRevenue- point*(1000));


            excelPlanReportDTOS.add(excelReportDTO);
        }

        return excelPlanReportDTOS;
    }

    private List<String> getNameColumnsPlan() {
        return Arrays.asList("STT", "Tên sản phẩm", "Đơn giá"
                , "Lượng đặt hàng", "Đã bán thành công", "Doanh thu", "Khuyến mãi", "Hủy đơn", "Doanh thu thực");
    }

    private void setContentTables(List<ExcelReportDTO> planDTOList, XSSFSheet sheet, Integer rowIndex) {
        final Integer rowHeader = rowIndex;
        for (Integer i = 0; i < planDTOList.size(); i++) {
            rowIndex++;
            XSSFRow rows = sheet.createRow(rowIndex);
            excelExport.setContentTable(setContentInRow(planDTOList.get(i), i + 1), rows, sheet);
        }
        Integer numberOfColumn = sheet.getRow(rowHeader).getPhysicalNumberOfCells();
        excelExport.autosizeColumn(sheet, numberOfColumn);
    }

    private List<Object> setContentInRow(ExcelReportDTO dto, Integer index) {
        return Arrays.asList(index, dto.getProductName(), dto.getUnitPrice(), dto.getCountOrder(), dto.getQuantitySold(),
                dto.getTotalPrice(), dto.getPoint(), dto.getCancelOrder(), dto.getRealRevenue());
    }

    public List<ReportDTO> getAllReport(ReportDTO dto, Pageable pageable) {
        return reportRepository.findAllByReportCode(dto.getReportCode(), pageable)
                .stream()
                .map(items -> converter.reportToDTO(items))
                .collect(Collectors.toList());
    }

    public boolean detele(Long id) {
        try {
            reportRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
