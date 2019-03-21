package com.example.service;

import com.example.domain.PC;
import com.example.domain.Printer;
import com.example.repository.PrinterRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

public class PrinterService {
    @Autowired
    private PrinterRepo printerRepo;

    public Printer getPrinter(Integer printer_id) throws NoSuchElementException {
        Printer printer = printerRepo.findById(printer_id).get();
        if (printer == null) throw new NoSuchElementException();
        return printer;
    }

    public List<Printer> getAllPrinters() {
        return printerRepo.findAll();
    }

    @Transactional
    public void createPrinter(Printer printer) {
        printerRepo.save(printer);
    }

    @Transactional
    public void updatePC(Printer printer, Integer printer_id) throws NoSuchElementException {
        Printer printerNew = printerRepo.findById(printer_id).get();
        if (printer_id > 0) {
            throw new NoSuchElementException();
        }
        printerNew.setModel(printer.getModel());
        printerNew.setColor(printer.getColor());
        printerNew.setType(printer.getType());
        printerNew.setPrice(printer.getPrice());

        printerRepo.save(printerNew);
    }

    @Transactional
    public void deletePC(Integer printer_id) throws NoSuchElementException {
        Printer printer = printerRepo.findById(printer_id).get();
        if (printer == null) throw new NoSuchElementException();
        printerRepo.delete(printer);
    }
}
