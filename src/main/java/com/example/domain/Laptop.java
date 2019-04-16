package com.example.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="laptop")
public class Laptop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="laptop_id", nullable = false)
    private Long id;
    @Column(name="model", nullable = false, length = 45)
    private Long model;
    @Column(name="ram")
    private short ram;
    @Column(name="processor_speed", nullable = false)
    private double speed;
    @Column(name="hd")
    private double hd;
    @Column(name="price")
    private double price;
    @Column(name="screen_square", nullable = false)
    private int screen;

//    @ManyToOne
//    @JoinColumn(name = "model", referencedColumnName = "model", insertable=false, updatable=false)
//    private Product product;

    public Laptop() {}

    public Laptop(Long model, short ram, double speed, double hd, double price, int screen, User author) {
        this.model = model;
        this.ram = ram;
        this.speed = speed;
        this.hd = hd;
        this.price = price;
        this.screen = screen;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getModel() { return model; }

    public void setModel(Long model) { this.model = model; }

    public short getRam() { return ram; }

    public void setRam(short ram) { this.ram = ram; }

    public double getHd() { return hd; }

    public void setHd(double hd) { this.hd = hd; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public int getScreen() { return screen; }

    public void setScreen(int screen) { this.screen = screen; }

    public double getSpeed() { return speed; }

    public void setSpeed(double speed) { this.speed = speed; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop)) return false;
        Laptop laptop = (Laptop) o;
        return ram == laptop.ram &&
                Double.compare(laptop.speed, speed) == 0 &&
                Double.compare(laptop.hd, hd) == 0 &&
                Double.compare(laptop.price, price) == 0 &&
                screen == laptop.screen &&
                id.equals(laptop.id) &&
                model.equals(laptop.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, ram, speed, hd, price, screen);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", ram=" + ram +
                ", speed=" + speed +
                ", hd=" + hd +
                ", price=" + price +
                ", screen=" + screen +
                '}';
    }
}