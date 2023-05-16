package com.test.neoris.service.movement;

import com.test.neoris.entities.Account;
import com.test.neoris.entities.Movement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovementService {

    public List<Movement> findAllMovements();

    public ResponseEntity<Movement> findMovementById(Integer id);

    public ResponseEntity<String> saveMovement(Movement movementNew);

    public ResponseEntity<String> deleteMovement(Integer id);

    public ResponseEntity<String> updateMovement(Movement movementNew, Integer id);
}
