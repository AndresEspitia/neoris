package com.test.neoris.controllers.account;


import com.test.neoris.entities.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountController {

    public List<Account> getAccounts();

    public ResponseEntity<Account> getAccountByNumber(int number);

    public ResponseEntity<String> addAccount(Account account);

    public ResponseEntity<String> deleteAccount(int number);

    public ResponseEntity<String> updateAccount(Account accountNew, int number);
}
