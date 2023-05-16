package com.test.neoris.controllers.movements;

import com.test.neoris.entities.Movement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovementController {

    public List<Movement> getMovements();

    public ResponseEntity<Movement> getMovementById(int id);

    public ResponseEntity<String> addMovement(Movement movement);

    public ResponseEntity<String> deleteMovement(int id);

    public ResponseEntity<String> updateMovement(Movement movement, int id);
}
