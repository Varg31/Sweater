package com.example.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="product")
public class Product implements Serializable {
    @Column(name="maker", nullable = false, length = 10)
    private String maker;
    @Id
    @Column(name="model", unique = true, nullable = false)
    private Long model;
    @Column(name="type", length = 45)
    private String type;

//    @OneToMany(mappedBy = "product")
//    private Set<Laptop> laptops;
    @OneToMany(mappedBy = "product")
    private Set<PC> pcList;
    @OneToMany(mappedBy = "product")
    private Set<Printer> printers;

    public Product() {
    }

    public Product(String maker, Long model, String type) {
        this.maker = maker;
        this.model = model;
        this.type = type;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Long getModel() {
        return model;
    }

    public void setModel(Long model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public Set<Laptop> getLaptops() { return laptops; }
//
//    public void setLaptops(Set<Laptop> leptops) { this.laptops = leptops; }

    public Set<PC> getPcList() { return pcList; }

    public void setPcList(Set<PC> pcList) { this.pcList = pcList; }

    public Set<Printer> getPrinters() { return printers; }

    public void setPrinters(Set<Printer> printers) { this.printers = printers; }
}
