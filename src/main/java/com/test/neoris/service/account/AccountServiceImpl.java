package com.test.neoris.service.account;

import com.test.neoris.entities.Account;
import com.test.neoris.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public ResponseEntity<Account> findAccountByNumber(Integer number) {
        try {
            Optional<Account> account = accountRepository.findById(number);
            Account foundAccount = account.orElseThrow(() -> new EntityNotFoundException("No se encontró cuenta con numero : " + number));
            return ResponseEntity.ok(foundAccount);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> saveAccount(Account accountNew) {
        accountNew.setAvailableBalance(accountNew.getInitialBalance());
         accountRepository.save(accountNew);
        return ResponseEntity.ok("Cuenta agregada exitosamente");
    }

    @Override
    public ResponseEntity<String> deleteAccount(Integer id) {
        if (accountRepository.findById(id).isPresent()) {
            accountRepository.deleteById(id);
            return ResponseEntity.ok("Cuenta eliminada correctamente.");
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<String> updateAccount(Account accountNew, Integer id) {
        if (accountRepository.findById(id).isPresent()) {
            Account newAccount = new Account();
            newAccount.setAccountId(id);
            newAccount.setNumberAccount(accountNew.getNumberAccount());
            newAccount.setStatus(accountNew.isStatus());
            newAccount.setType(accountNew.getType());
            newAccount.setInitialBalance(accountNew.getInitialBalance());
            newAccount.setCustomer(accountNew.getCustomer());

            accountRepository.save(newAccount);

            return ResponseEntity.ok("Cuenta actualizada exitosamente");
        }
        return ResponseEntity.notFound().build();
    }
}
