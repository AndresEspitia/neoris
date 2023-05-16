package com.test.neoris.service.account;

import static org.mockito.Mockito.*;

import com.test.neoris.entities.Account;
import com.test.neoris.entities.Customer;
import com.test.neoris.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testFindAccountByNumber() {

        Account account = new Account();
        account.setAccountId(4);
        account.setNumberAccount(990715);
        account.setType("CORRIENTE");
        account.setStatus(true);
        when(accountRepository.findById(4)).thenReturn(Optional.of(account));

        ResponseEntity<Account> response = accountService.findAccountByNumber(4);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(account, response.getBody());
    }

    @Test
    public void testDeleteAccount() {

        Integer id = 1;
        when(accountRepository.findById(id)).thenReturn(Optional.of(new Account()));

        ResponseEntity<String> response = accountService.deleteAccount(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cuenta eliminada correctamente.", response.getBody());
    }

    @Test
    public void testSaveAccount() {
        Account accountNew = new Account();
        accountNew.setInitialBalance(100.0);
        accountNew.setStatus(true);
        accountNew.setType("CORRIENTE");
        accountNew.setAccountId(1);
        accountNew.setCustomer(2);

        when(accountRepository.save(any(Account.class))).thenReturn(accountNew);
        ResponseEntity<String> response = accountService.saveAccount(accountNew);

        Assertions.assertEquals("Cuenta agregada exitosamente", response.getBody());
        verify(accountRepository).save(accountNew);
        Assertions.assertEquals(100.0, accountNew.getAvailableBalance());
    }

    @Test
    public void testUpdateAccount() {
        Integer id = 1;
        Account accountNew = new Account();
        accountNew.setNumberAccount(123456);
        accountNew.setStatus(true);
        accountNew.setType("AHORROS");
        accountNew.setInitialBalance(1000.0);
        Customer customer = new Customer();
        customer.setClientId(1);
        accountNew.setCustomer(1);

        Account existingAccount = new Account();
        existingAccount.setAccountId(id);

        when(accountRepository.findById(id)).thenReturn(Optional.of(existingAccount));

        ResponseEntity<String> response = accountService.updateAccount(accountNew, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cuenta actualizada exitosamente", response.getBody());

        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testFindAllAccounts() {

        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account());
        accountList.add(new Account());
        Mockito.when(accountRepository.findAll()).thenReturn(accountList);

        List<Account> result = accountService.findAllAccounts();

        assertEquals(accountList, result);

        Mockito.verify(accountRepository, Mockito.times(1)).findAll();
    }

}