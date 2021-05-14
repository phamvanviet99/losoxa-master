package com.phamvanviet.losoxa.processor;

import com.phamvanviet.losoxa.constant.ReportConstant;
import com.phamvanviet.losoxa.entity.Report;
import com.phamvanviet.losoxa.model.dto.ReportDTO;
import com.phamvanviet.losoxa.model.dto.ServiceResult;
import com.phamvanviet.losoxa.model.request.report.ReportRequest;
import com.phamvanviet.losoxa.repository.ReportRepository;
import com.phamvanviet.losoxa.service.RevenueReportService;
import com.phamvanviet.losoxa.util.DateUtils;
import com.phamvanviet.losoxa.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
public class RevenueReportProcessor {

    private final Logger logger = LogManager.getLogger(RevenueReportProcessor.class);
    @Autowired
    private RevenueReportService revenueReportService;
    @Autowired
    private ReportRepository reportRepository;




    public ServiceResult getLinkFileByPropertyDetail(ReportRequest dto){
        try {
            String fileName = revenueReportService.exportAll(dto);
            String path;
            if (fileName != "false"){
                path = ReportConstant.URL_REPORT_DOWNLOAD+ fileName;
                reportRepository.save(setReportRevenue(dto,path));
            }else {
                path = "false";
            }
            return new ServiceResult(path,"success","200");


        }catch (Exception e){
            return new ServiceResult(null,"false","500");
        }

    }

    private Report setReportRevenue(ReportRequest dto, String path) {
        String starDate = DateUtils.getDate(dto.getStartDate()) +"/"+ DateUtils.getMonth(dto.getStartDate())+"/"+DateUtils.getYear(dto.getStartDate());
        String endDate = DateUtils.getDate(dto.getEndDate()) +"/"+ DateUtils.getMonth(dto.getEndDate())+"/"+DateUtils.getYear(dto.getEndDate());
        Report report = new Report();
        report.setReportCode(ReportConstant.REVENUE_EXPORT);
        StringBuilder fileName = new StringBuilder();
        fileName.append(" BAO-CAO-DOANH-SO-BAN-HANG ").append("  ")
                .append(starDate).append(" - ")
                .append(endDate).append(" ");
        report.setNameFile(fileName.toString());
        report.setPath(path);
        report.setUrl(path);
        report.setCreatedAt(new Date());
        report.setCreatedBy(SecurityUtils.getPrinciple().getUsername());
        return report;
    }

    public ServiceResult getRevenue(Integer size, Integer page, ReportDTO dto){
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        dto.setReportCode(ReportConstant.REVENUE_EXPORT);
        Long totalItem = reportRepository.countAllByReportCode(dto.getReportCode());
        int totalPage = (int) Math.ceil((double) totalItem / size);
        List<ReportDTO> lists = revenueReportService.getAllReport(dto,pageable);
        return new ServiceResult(lists, totalPage, page);
    }


    public ServiceResult deleteRevenue(Long id){
        return new ServiceResult(revenueReportService.detele(id),"success","200");
    }


}
