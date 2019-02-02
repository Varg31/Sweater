package com.example.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="laptop")
public class PC implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="laptop_id", nullable = false)
    private Integer id;
    @Column(name="model", nullable = false, length = 45)
    private Long model;
    @Column(name="ram")
    private short ram;
    @Column(name="processor_speed", nullable = false)
    private double speed;
    @Column(name="hd")
    private double hd;
    @Column(name="cd", length = 10)
    private String cd;
    @Column(name="price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "model", referencedColumnName = "city_id")
    private Product product;

    public PC() {}

    public PC(Long model, short ram, double speed, double hd, String cd, double price) {
        this.model = model;
        this.ram = ram;
        this.speed = speed;
        this.hd = hd;
        this.cd = cd;
        this.price = price;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Long getModel() { return model; }

    public void setModel(Long model) { this.model = model; }

    public short getRam() { return ram; }

    public void setRam(short ram) { this.ram = ram; }

    public double getHd() { return hd; }

    public void setHd(double hd) { this.hd = hd; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public double getSpeed() { return speed; }

    public void setSpeed(double speed) { this.speed = speed; }

    public String getCd() { return cd; }

    public void setCd(String cd) { this.cd = cd; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PC)) return false;
        PC pc = (PC) o;
        return ram == pc.ram &&
                Double.compare(pc.speed, speed) == 0 &&
                Double.compare(pc.hd, hd) == 0 &&
                Double.compare(pc.price, price) == 0 &&
                id.equals(pc.id) &&
                model.equals(pc.model) &&
                cd.equals(pc.cd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, ram, speed, hd, cd, price);
    }

    @Override
    public String toString() {
        return "PC{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", ram=" + ram +
                ", speed=" + speed +
                ", hd=" + hd +
                ", cd='" + cd + '\'' +
                ", price=" + price +
                '}';
    }
}