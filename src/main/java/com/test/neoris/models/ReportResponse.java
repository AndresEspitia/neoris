package com.test.neoris.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {
    private Date date;
    private String customer;
    private int numberAccount;
    private String type;
    private double initialBalance;
    private boolean status;
    private double availableBalance;
    private double valueMov;
}
