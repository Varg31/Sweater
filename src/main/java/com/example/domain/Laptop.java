package com.example.domain;

import javax.persistence.*;

@Entity
@Table(name="laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String model;
    private short ram;
    private double hd;
    private int price;

    public Laptop() {}

    public Laptop(String model, short ram, double hd, int price) {
        this.model = model;
        this.ram = ram;
        this.hd = hd;
        this.price = price;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public short getRam() { return ram; }

    public void setRam(short ram) { this.ram = ram; }

    public double getHd() { return hd; }

    public void setHd(double hd) { this.hd = hd; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }
}