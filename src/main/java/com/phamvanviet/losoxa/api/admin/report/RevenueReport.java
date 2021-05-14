package com.phamvanviet.losoxa.api.admin.report;

import com.phamvanviet.losoxa.model.dto.ReportDTO;
import com.phamvanviet.losoxa.model.dto.ServiceResult;
import com.phamvanviet.losoxa.model.request.report.ReportRequest;
import com.phamvanviet.losoxa.processor.RevenueReportProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/report/revenue")
public class RevenueReport {
    @Autowired
    private RevenueReportProcessor processor;

    @PostMapping
    public ResponseEntity<ServiceResult> exportAll(@RequestBody(required = false) ReportRequest dto) {
        return new ResponseEntity<>(processor.getLinkFileByPropertyDetail(dto), HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<ServiceResult> getList(
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestBody(required = false) ReportDTO dto) {
        return new ResponseEntity<>(processor.getRevenue(size, page, dto), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResult> deleteReport(@PathVariable("id") Long id) {
        return new ResponseEntity<>(processor.deleteRevenue(id), HttpStatus.OK);
    }
}
