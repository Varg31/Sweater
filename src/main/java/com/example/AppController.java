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

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Laptop> laptops = laptopRepo.findAll();
        model.put("laptops", laptops);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam Long model, short ram, double speed, double hd, int price, int screen,
                      Map<String, Object> modelMap) {
        Laptop laptop = new Laptop(model, ram, speed, hd, price, screen);
        laptopRepo.save(laptop);

        Iterable<Laptop> laptops = laptopRepo.findAll();
        modelMap.put("laptops", laptops);

        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> modelMap) {
        Iterable<Laptop> laptops;
        if (filter != null && !filter.isEmpty()) {
            laptops = laptopRepo.findByScreen(Integer.parseInt(filter));
        } else {
            laptops = laptopRepo.findAll();
        }

        modelMap.put("laptops", laptops);
        return "main";
    }
}
