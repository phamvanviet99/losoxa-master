package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.Report;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Long countAllByReportCode(String reportCode);

    List<Report> findAllByReportCode(String reportCode, Pageable pageable);


    Report findReportById(Long id);
}
