package com.example.repository;

import com.example.domain.Printer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterRepo extends CrudRepository<Printer,Integer> {
}
