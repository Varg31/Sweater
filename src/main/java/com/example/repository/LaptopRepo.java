package com.example.repository;

import com.example.domain.Laptop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepo extends CrudRepository<Laptop, Integer> {
}
