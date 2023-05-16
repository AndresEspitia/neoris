package com.test.neoris.service.account;

import com.test.neoris.entities.Account;
import com.test.neoris.entities.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {

    public List<Account> findAllAccounts();

    public ResponseEntity<Account> findAccountByNumber(Integer number);

    public ResponseEntity<String> saveAccount(Account accountNew);

    public ResponseEntity<String> deleteAccount(Integer number);

    public ResponseEntity<String> updateAccount(Account accountNew, Integer number);
}
