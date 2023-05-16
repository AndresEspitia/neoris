package com.test.neoris.controllers.account;

import com.test.neoris.entities.Account;
import com.test.neoris.service.account.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@RestController
public class AccountImpl implements AccountController{

    private final AccountServiceImpl accountService;

    @GetMapping(path = "/accounts", produces = "application/json")
    @Override
    public List<Account> getAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping(path = "/accounts/{number}", produces = "application/json")
    @Override
    public ResponseEntity<Account> getAccountByNumber(@PathVariable int number) {
        try {
            return accountService.findAccountByNumber(number);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/account/add", produces = "application/json")
    @Override
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @DeleteMapping(path = "/account/delete/{number}", produces = "application/json")
    @Override
    public ResponseEntity<String> deleteAccount(@PathVariable int number) {
        return accountService.deleteAccount(number);
    }

    @PutMapping(path = "/account/update/{number}", produces = "application/json")
    @Override
    public ResponseEntity<String> updateAccount(@RequestBody Account accountNew, @PathVariable int number) {
        return accountService.updateAccount(accountNew, number);
    }
}
