package com.example.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="printer")
public class Printer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="printer_id", nullable = false)
    private Integer id;
    @Column(name="model", nullable = false, length = 45)
    private Long model;
    @Column(name="type", nullable = false, length = 45)
    private String type;
    @Column(name="color")
    private char color;
    @Column(name="price")
    private int price;
    @ManyToOne
    @JoinColumn(name = "model", referencedColumnName = "city_id")
    private Product product;

    public Printer() {
    }

    public Printer(Long model, String type, char color, int price) {
        this.model = model;
        this.type = type;
        this.color = color;
        this.price = price;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Long getModel() { return model; }

    public void setModel(Long model) { this.model = model; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public char getColor() { return color; }

    public void setColor(char color) { this.color = color; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Printer)) return false;
        Printer printer = (Printer) o;
        return color == printer.color &&
                price == printer.price &&
                id.equals(printer.id) &&
                model.equals(printer.model) &&
                type.equals(printer.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, type, color, price);
    }

    @Override
    public String toString() {
        return "Printer {" +
                "printer_id=" + id +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", color=" + color +
                ", price=" + price +
                '}';
    }
}