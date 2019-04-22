package com.example.controller;

import com.example.domain.Laptop;
import com.example.domain.Message;
import com.example.domain.User;
import com.example.repository.LaptopRepo;
import com.example.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    private LaptopRepo laptopRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Value("${upload.path}")
    private String uploadPath;

    public MainController(LaptopRepo laptopRepo) {
        this.laptopRepo = laptopRepo;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Laptop> laptops;
        if (filter != null && !filter.isEmpty()) {
            laptops = laptopRepo.findByScreen(Integer.parseInt(filter));
        } else {
            laptops = laptopRepo.findAll();
        }
        model.addAttribute("laptops", laptops);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam Long model, short ram, double speed, double hd, int price, int screen,
            Model modelMap) {
        Laptop laptop = new Laptop(model, ram, speed, hd, price, screen, user);
        laptopRepo.save(laptop);

        Iterable<Laptop> laptops = laptopRepo.findAll();
        modelMap.addAttribute("laptops", laptops);

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
            @RequestParam String tag, Model modelMap,
            @RequestParam("file") MultipartFile file) throws IOException {
        Message message = new Message(text, tag, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "\\" + resultFilename));

            message.setFilename(resultFilename);
        }
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        modelMap.addAttribute("messages", messages);

        return "message";
    }
}
