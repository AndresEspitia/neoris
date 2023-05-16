package com.test.neoris.controllers.accountReport;

import com.test.neoris.models.ReportResponse;

import java.util.List;

public interface AccountReportController {

    public List<ReportResponse> generateReport(String startDate, String endDate, int idCustomer);
}
