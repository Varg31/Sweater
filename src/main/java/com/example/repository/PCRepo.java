package com.example.repository;

import com.example.domain.PC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCRepo extends JpaRepository<PC, Integer> {
}
