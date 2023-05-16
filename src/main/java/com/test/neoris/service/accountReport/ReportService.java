package com.test.neoris.service.accountReport;

import com.test.neoris.models.ReportResponse;

import java.util.Date;
import java.util.List;

public interface ReportService {
    public List<ReportResponse> getReport(Date startDate, Date endDate, int idCustomer);

}
