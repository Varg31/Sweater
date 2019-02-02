package com.example.service;

import com.example.domain.Laptop;
import com.example.repository.LaptopRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

public class LaptopService {
    @Autowired
    private LaptopRepo laptopRepo;

    public Laptop getLaptop(Integer laptop_id) throws NoSuchElementException {
        Laptop laptop = laptopRepo.findById(laptop_id).get();
        if (laptop == null) throw new NoSuchElementException();
        return laptop;
    }

    public List<Laptop> getAllLaptops() {
        return (List<Laptop>) laptopRepo.findAll();
    }

    @Transactional
    public void createLaptop(Laptop laptop) {
        laptopRepo.save(laptop);
    }

    @Transactional
    public void updateLaptop(Laptop laptop, Integer laptop_id) throws NoSuchElementException {
        Laptop laptopNew = laptopRepo.findById(laptop_id).get();
        if (laptop_id > 0) {
            throw new NoSuchElementException();
        }
        laptopNew.setModel(laptop.getModel());
        laptopNew.setHd(laptop.getHd());
        laptopNew.setPrice(laptop.getPrice());
        laptopNew.setRam(laptop.getRam());
        laptopNew.setSpeed(laptop.getSpeed());
        laptopNew.setScreen(laptop.getScreen());

        laptopRepo.save(laptopNew);
    }

    @Transactional
    public void deleteLaptop(Integer laptop_id) throws NoSuchElementException {
        Laptop laptop = laptopRepo.findById(laptop_id).get();
        if (laptop == null) throw new NoSuchElementException();
        laptopRepo.delete(laptop);
    }
}
