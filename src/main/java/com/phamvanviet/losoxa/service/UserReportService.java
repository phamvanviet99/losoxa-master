package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.Report;
import com.phamvanviet.losoxa.mapper.Converter;
import com.phamvanviet.losoxa.model.dto.ExcelReportDTO;
import com.phamvanviet.losoxa.model.dto.ReportDTO;
import com.phamvanviet.losoxa.model.request.report.ReportRequest;
import com.phamvanviet.losoxa.repository.ReportRepository;
import com.phamvanviet.losoxa.repository.UserRepository;
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
public class UserReportService {

    @Autowired
    private ExcelExport excelExport;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Converter converter;
    @Autowired
    private ReportRepository reportRepository;

    public String exportAll(ReportRequest dto) throws IOException, ParseException {
        String starDate = DateUtils.getDate(dto.getStartDate()) + "/" + DateUtils.getMonth(dto.getStartDate()) + "/" + DateUtils.getYear(dto.getStartDate());
        String endDate = DateUtils.getDate(dto.getEndDate()) + "/" + DateUtils.getMonth(dto.getEndDate()) + "/" + DateUtils.getYear(dto.getEndDate());
        List<ExcelReportDTO> list = getCoursePlan(dto);
        String nameFile = UUID.randomUUID().toString() + ".xlsx";
        String name = "Bao-cao-khach-hang-than-thiet" + nameFile;
        StringBuilder path = new StringBuilder("D://Losoxa//report//" + "/LoyalCustomer/" + name);
        XSSFCell cell = null;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Đề xuất");

        excelExport.setTitle(cell, sheet, "BÁO CÁO DANH SÁCH KHÁCH HÀNG THÂN THIẾT", new CellRangeAddress(4, 4, 0, 4), 4, 0);
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

    private List<ExcelReportDTO> getCoursePlan(ReportRequest dto) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(formatter.format(dto.getStartDate()));
        Date endDate = formatter.parse(formatter.format(dto.getEndDate()));


        List<ExcelReportDTO> excelPlanReportDTOS = new ArrayList<>();

        List<Tuple> reportRequests = userRepository.findAllByOrderAndPredictMonth(startDate, endDate);

        for (Tuple userResponse : reportRequests) {
            ExcelReportDTO excelReportDTO = new ExcelReportDTO();
//                if(dto.getPoscodeId() == null)
//                    excelReportDTO.setNameUnit("Tổng công ty");
//                else
            if (userResponse.get(0).toString() != null)
                excelReportDTO.setUserName(userResponse.get(0).toString());
            else excelReportDTO.setUserName("");

            if (userResponse.get(1).toString() != null)
                excelReportDTO.setFullName(userResponse.get(1).toString());
            else excelReportDTO.setFullName("");

            if (userResponse.get(2).toString() != null)
                excelReportDTO.setEmail(userResponse.get(2).toString());
            else excelReportDTO.setEmail("");

            if (userResponse.get(3).toString() != null)
                excelReportDTO.setCountOrder(Integer.parseInt(userResponse.get(3).toString()));
            else excelReportDTO.setCountOrder(0);

            if (userResponse.get(4).toString() != null)
                excelReportDTO.setTotalPrice(Long.parseLong(userResponse.get(4).toString()));
            else excelReportDTO.setTotalPrice(0L);

            excelPlanReportDTOS.add(excelReportDTO);
        }

        return excelPlanReportDTOS;
    }

    private List<String> getNameColumnsPlan() {
        return Arrays.asList("STT", "Tên đăng nhập", "Tên đầy đủ"
                , "Email", "Số lần đặt hàng", "Tổng số tiền đặt hàng");
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

        return Arrays.asList(index, dto.getUserName(), dto.getFullName(), dto.getEmail()
                , dto.getCountOrder(), dto.getTotalPrice());
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
