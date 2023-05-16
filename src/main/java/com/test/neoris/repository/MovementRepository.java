package com.test.neoris.repository;

import com.test.neoris.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Integer> {

}
