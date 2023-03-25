package com.example.cslink.procedure.model;

import com.example.cslink.user.model.entity.Cosmetologist;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="procedures")
public class Procedure {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cosmetologist_id")
    private Cosmetologist cosmetologists;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public Cosmetologist getCosmetologist() {
        return cosmetologists;
    }

    public void setCosmetologist(Cosmetologist cosmetologist) {
        this.cosmetologists=cosmetologist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price=price;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", cosmetologists=" + cosmetologists +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Procedure procedure=(Procedure) o;
        return Objects.equals(id, procedure.id)
                && Objects.equals(name, procedure.name)
                && Objects.equals(description, procedure.description)
                && Objects.equals(price, procedure.price)
                && Objects.equals(cosmetologists, procedure.cosmetologists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, cosmetologists);
    }


}
