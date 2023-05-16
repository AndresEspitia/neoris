package com.test.neoris.controllers.accountReport;

import com.test.neoris.models.ReportResponse;
import com.test.neoris.service.accountReport.ReportServiceImpl;
import lombok.AllArgsConstructor;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@RestController
public class AccountReportImpl implements AccountReportController{

    private final ReportServiceImpl reportService;

    @GetMapping(path = "/reports", produces = "application/json")
    @Override
    public List<ReportResponse> generateReport(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("idCustomer") int idCustomer) {

        Date start = java.sql.Date.valueOf(LocalDate.parse(startDate));
        Date end = java.sql.Date.valueOf(LocalDate.parse(endDate));

        return reportService.getReport(start, end, idCustomer);
    }
}
