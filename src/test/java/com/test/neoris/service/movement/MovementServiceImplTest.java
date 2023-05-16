package com.test.neoris.service.movement;

import static org.mockito.Mockito.*;
import com.test.neoris.entities.Movement;
import com.test.neoris.repository.MovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class MovementServiceImplTest {

    @Mock
    private MovementRepository movementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private MovementServiceImpl movementService;

    @Test
    public void testDeleteMovement() {

        Integer id = 1;
        Movement movement = new Movement();
        when(movementRepository.findById(id)).thenReturn(Optional.of(movement));
        ResponseEntity<String> response = movementService.deleteMovement(id);

        verify(movementRepository, times(1)).deleteById(id);
        assertEquals(ResponseEntity.ok("Movimiento eliminado correctamente."), response);
    }

    @Test
    public void testFindMovementById() {
        Movement movement = new Movement();
        movement.setMovementId(1);
        movement.setMovement("Abono credito");

        when(movementRepository.findById(1)).thenReturn(Optional.of(movement));

        ResponseEntity<Movement> response = movementService.findMovementById(1);

        verify(movementRepository, times(1)).findById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movement, response.getBody());
    }

    @Test
    public void testFindMovementByIdNotFound() {
        when(movementRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Movement> response = movementService.findMovementById(1);

        verify(movementRepository, times(1)).findById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void updateMovement() {

        Integer existingMovementId = 1;
        Movement existingMovement = new Movement();
        existingMovement.setMovementId(existingMovementId);
        existingMovement.setMovement("Retiro de 100");
        existingMovement.setStatus(true);
        existingMovement.setType("DEBITO");
        existingMovement.setValue_mov(100.0);
        existingMovement.setAccountId("1");
        existingMovement.setNumberAccount(123456);
        when(movementRepository.findById(existingMovementId)).thenReturn(Optional.of(existingMovement));

        Movement updatedMovement = new Movement();
        updatedMovement.setMovement("Credito de 50");
        updatedMovement.setStatus(false);
        updatedMovement.setType("CREDITO");
        updatedMovement.setValue_mov(50.0);
        ResponseEntity<String> response = movementService.updateMovement(updatedMovement, existingMovementId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Movimiento actualizado exitosamente", response.getBody());
        verify(movementRepository, times(1)).save(any(Movement.class));
    }
}