package com.test.neoris.service.movement;

import com.test.neoris.entities.Account;
import com.test.neoris.entities.Movement;
import com.test.neoris.exceptions.balanceNotAbailable;
import com.test.neoris.repository.AccountRepository;
import com.test.neoris.repository.MovementRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<Movement> findAllMovements() {
        return movementRepository.findAll();
    }

    @Override
    public ResponseEntity<Movement> findMovementById(Integer id) {
        try {
            Optional<Movement> movement = movementRepository.findById(id);
            Movement foundMovement = movement.orElseThrow(() -> new EntityNotFoundException("No se encontró cuenta con numero : " + id));
            return ResponseEntity.ok(foundMovement);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> saveMovement(Movement movementNew) {

        Optional<Account> account = accountRepository.findById(Integer.valueOf(movementNew.getAccountId()));
        if (account.isPresent()) {
            Account updateAccount = account.get();
            double oldBalance = updateAccount.getInitialBalance(); // saldo inicial
            double valueMov = movementNew.getValue_mov(); // valor del movimiento
            double availableBalance = 0; // saldo disponible

            if (movementNew.getType().equals("CREDITO")) {
                availableBalance = oldBalance + valueMov;
            } else if (movementNew.getType().equals("DEBITO")) {
                if (oldBalance < valueMov) {
                    throw new balanceNotAbailable("Saldo no disponible");
                }
                availableBalance = oldBalance - valueMov;
            }
            updateAccount.setInitialBalance(oldBalance);
            updateAccount.setAvailableBalance(availableBalance);
        }
        movementRepository.save(movementNew);
        return ResponseEntity.ok("Movimiento agregado exitosamente");
    }

    @Override
    public ResponseEntity<String> deleteMovement(Integer id) {
        if (movementRepository.findById(id).isPresent()) {
            movementRepository.deleteById(id);
            return ResponseEntity.ok("Movimiento eliminado correctamente.");
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<String> updateMovement(Movement movementNew, Integer id) {
        Optional<Movement> findMov = movementRepository.findById(id);
        if (findMov.isPresent()) {
            Movement findClientId = findMov.get();
            Movement newMovement = new Movement();
            newMovement.setMovementId(id);
            newMovement.setMovement(movementNew.getMovement());
            newMovement.setStatus(movementNew.isStatus());
            newMovement.setType(movementNew.getType());
            newMovement.setValue_mov(movementNew.getValue_mov());
            newMovement.setAccountId(findClientId.getAccountId());
            newMovement.setNumberAccount(findClientId.getNumberAccount());
            movementRepository.save(newMovement);

            return ResponseEntity.ok("Movimiento actualizado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }
}
