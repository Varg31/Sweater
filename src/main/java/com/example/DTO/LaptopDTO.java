package com.example.DTO;

import java.util.NoSuchElementException;
import com.example.controller.ProductController;
import com.example.domain.Laptop;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class LaptopDTO extends ResourceSupport {
    Laptop laptop;

    public LaptopDTO(Laptop laptop, Link selfLink) throws NoSuchElementException {
        this.laptop = laptop;
        add(selfLink);
        //add(linkTo(methodOn(ProductController.class)));
    }

    public Long getCode() { return laptop.getId(); }
    public Long getModel() { return laptop.getModel(); }
    public short getRam() { return laptop.getRam(); }
    public double getSpeed() { return laptop.getSpeed(); }
    public double getHd() { return laptop.getHd(); }
    public int getScreen() {return laptop.getScreen(); }
    public double getPrice() { return laptop.getPrice(); }
}
