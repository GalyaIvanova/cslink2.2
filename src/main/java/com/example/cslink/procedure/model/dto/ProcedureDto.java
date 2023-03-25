package com.example.cslink.procedure.model.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProcedureDto {
    private Long id;
    private Long cosmetologistId;
    private String name;
    private String description;
    private BigDecimal price;
    //private Set<String> tags;


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

//    public List<String> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<String> tags) {
//        this.tags=tags;
//    }

    public Long getCosmetologistId() {
        return cosmetologistId;
    }

    public void setCosmetologistId(Long cosmetologistId) {
        this.cosmetologistId=cosmetologistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcedureDto that=(ProcedureDto) o;
        return Objects.equals(id, that.id) && Objects.equals(cosmetologistId, that.cosmetologistId) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cosmetologistId, name, description, price);
    }

    @Override
    public String toString() {
        return "ProcedureDto{" +
                "id=" + id +
                ", cosmetologistId=" + cosmetologistId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}

