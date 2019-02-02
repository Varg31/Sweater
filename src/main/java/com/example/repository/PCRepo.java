package com.example.repository;

import com.example.domain.PC;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCRepo extends CrudRepository<PC, Integer> {
}
