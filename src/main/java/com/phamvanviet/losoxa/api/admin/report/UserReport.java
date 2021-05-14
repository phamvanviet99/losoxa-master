package com.phamvanviet.losoxa.api.admin.report;

import com.phamvanviet.losoxa.model.dto.ReportDTO;
import com.phamvanviet.losoxa.model.dto.ServiceResult;
import com.phamvanviet.losoxa.model.request.report.ReportRequest;
import com.phamvanviet.losoxa.processor.UserReportProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/report/user")
public class UserReport {


    @Autowired
    private UserReportProcessor processor;

    @PostMapping("/loyal-customer")
    public ResponseEntity<ServiceResult> exportAll(@RequestBody(required = false) ReportRequest dto) {
        return new ResponseEntity<>(processor.getLinkFileByPropertyDetail(dto), HttpStatus.OK);
    }

    @PostMapping("/list-loyal-customer")
    public ResponseEntity<ServiceResult> getListJobTitle(
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestBody(required = false) ReportDTO dto) {
        return new ResponseEntity<>(processor.getLoyalCustomer(size, page, dto), HttpStatus.OK);
    }


    @DeleteMapping("/delete-loyal/{id}")
    public ResponseEntity<ServiceResult> deleteReport(@PathVariable("id") Long id) {
        return new ResponseEntity<>(processor.deleteLoyalCustomer(id), HttpStatus.OK);
    }
}
