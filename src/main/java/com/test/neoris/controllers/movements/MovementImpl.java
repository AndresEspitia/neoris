package com.test.neoris.controllers.movements;

import com.test.neoris.entities.Movement;
import com.test.neoris.exceptions.balanceNotAbailable;
import com.test.neoris.service.movement.MovementServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@RestController
public class MovementImpl implements MovementController{

    private final MovementServiceImpl movementService;

    @GetMapping(path = "/movements", produces = "application/json")
    @Override
    public List<Movement> getMovements() {
        return movementService.findAllMovements();
    }

    @GetMapping(path = "/movements/{id}", produces = "application/json")
    @Override
    public ResponseEntity<Movement> getMovementById(@PathVariable int id) {
        try {
            return movementService.findMovementById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/movements/add", produces = "application/json")
    @Override
    public ResponseEntity<String> addMovement(@RequestBody Movement movement) {
        try {
            return movementService.saveMovement(movement);
        } catch (balanceNotAbailable e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/movements/delete/{id}", produces = "application/json")
    @Override
    public ResponseEntity<String> deleteMovement(@PathVariable int id) {
        return movementService.deleteMovement(id);
    }

    @PutMapping(path = "/movements/update/{id}", produces = "application/json")
    @Override
    public ResponseEntity<String> updateMovement(@RequestBody Movement movement, @PathVariable int id) {
        return movementService.updateMovement(movement, id);
    }
}
