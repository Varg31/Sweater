package com.example.service;

import com.example.domain.PC;
import com.example.repository.PCRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

public class PCService {
    @Autowired
    private PCRepo pcRepo;

    public PC getPC(Integer pc_id) throws NoSuchElementException {
        PC pc = pcRepo.findById(pc_id).get();
        if (pc == null) throw new NoSuchElementException();
        return pc;
    }

    public List<PC> getAllLaptops() {
        return pcRepo.findAll();
    }

    @Transactional
    public void createPC(PC pc) {
        pcRepo.save(pc);
    }

    @Transactional
    public void updatePC(PC pc, Integer pc_id) throws NoSuchElementException {
        PC pcNew = pcRepo.findById(pc_id).get();
        if (pc_id > 0) {
            throw new NoSuchElementException();
        }
        pcNew.setPrice(pc.getPrice());
        pcNew.setModel(pc.getModel());
        pcNew.setHd(pc.getHd());
        pcNew.setRam(pc.getRam());
        pcNew.setSpeed(pc.getSpeed());
        pcNew.setCd(pc.getCd());

        pcRepo.save(pcNew);
    }

    @Transactional
    public void deletePC(Integer pc_id) throws NoSuchElementException {
        PC pc = pcRepo.findById(pc_id).get();
        if (pc == null) throw new NoSuchElementException();
        pcRepo.delete(pc);
    }
}
