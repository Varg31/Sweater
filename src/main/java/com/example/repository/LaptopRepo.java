package com.example.repository;

import com.example.domain.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepo extends JpaRepository<Laptop, Integer> {
    List<Laptop> findByScreen(Integer laptopId);
}
