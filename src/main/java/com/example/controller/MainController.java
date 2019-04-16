package com.example.controller;

import com.example.domain.Laptop;
import com.example.domain.Message;
import com.example.domain.User;
import com.example.repository.LaptopRepo;
import com.example.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private LaptopRepo laptopRepo;
    @Autowired
    private MessageRepo messageRepo;

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
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam Long model, short ram, double speed, double hd, int price, int screen,
                      Map<String, Object> modelMap) {
        Laptop laptop = new Laptop(model, ram, speed, hd, price, screen, user);
        laptopRepo.save(laptop);

        Iterable<Laptop> laptops = laptopRepo.findAll();
        modelMap.put("laptops", laptops);

        return "main";
    }
    @PostMapping("/filter")
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

    @GetMapping("/message")
    public String message(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "message";
    }

    @PostMapping("/message")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Model modelMap) {
        Message message = new Message(text, tag, user);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        modelMap.addAttribute("messages", messages);

        return "message";
    }
}
