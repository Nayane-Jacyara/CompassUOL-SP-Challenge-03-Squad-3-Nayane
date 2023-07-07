package br.com.compassuol.pb.challenge.msproducts.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_category")
public class Category {
    @Id
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
