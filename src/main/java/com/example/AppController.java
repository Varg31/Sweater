package com.example;

import com.example.domain.Laptop;
import com.example.repository.LaptopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AppController {
    @Autowired
    private LaptopRepo laptopRepo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Laptop> laptops = laptopRepo.findAll();
        model.put("laptops", laptops);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String model, short ram, double hd, int price, Map<String, Object> modelMap) {
        Laptop laptop = new Laptop(model, ram, hd, price);
        laptopRepo.save(laptop);

        Iterable<Laptop> laptops = laptopRepo.findAll();
        modelMap.put("laptops", laptops);

        return "main";
    }
}
