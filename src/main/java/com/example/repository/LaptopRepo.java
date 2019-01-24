package com.example.repository;

import com.example.domain.Laptop;
import org.springframework.data.repository.CrudRepository;

public interface LaptopRepo extends CrudRepository<Laptop, Integer> {
}
