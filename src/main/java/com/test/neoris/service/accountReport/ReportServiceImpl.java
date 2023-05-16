package com.test.neoris.service.accountReport;

import com.test.neoris.models.ReportResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService{

    private final EntityManager entityManager;

    @Override
    public List<ReportResponse> getReport(Date startDate, Date endDate, int idCustomer) {

        String query = "SELECT m.date, m.value_mov, c.name, a.numberAccount, a.type, a.initialBalance, a.status, a.availableBalance "
                + "FROM Movement m "
                + "INNER JOIN Account a ON m.accountId = a.accountId "
                + "INNER JOIN Customer c ON a.customer = c.clientId "
                + "WHERE c.clientId = :idCustomer AND m.date BETWEEN :startDate AND :endDate";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query, Object[].class)
                .setParameter("idCustomer", idCustomer)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);


        List<ReportResponse> reportResponses = new ArrayList<>();
        for (Object[] result : typedQuery.getResultList()) {
            ReportResponse reportResponse = new ReportResponse();
            reportResponse.setDate((Date) result[0]);
            reportResponse.setCustomer((String) result[2]);
            reportResponse.setNumberAccount(Integer.parseInt(String.valueOf((Integer) result[3])));
            reportResponse.setType((String) result[4]);
            reportResponse.setInitialBalance((Double) result[5]);
            reportResponse.setStatus((Boolean) result[6]);
            reportResponse.setValueMov((Double) result[1]);
            reportResponse.setAvailableBalance((Double) result[7]);
            reportResponses.add(reportResponse);
        }

        Collections.sort(reportResponses, new Comparator<ReportResponse>() {
            @Override
            public int compare(ReportResponse reportOne, ReportResponse reportTwo) {
                return reportTwo.getDate().compareTo(reportOne.getDate());
            }
        });

        return reportResponses;
    }
}
