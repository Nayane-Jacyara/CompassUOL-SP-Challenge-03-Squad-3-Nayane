package br.com.compassuol.pb.challenge.msproducts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @NotBlank(message = "Description is required")
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank(message = "Name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Image URL is required")
    @Column(name = "imgUrl", nullable = false)
    private String imgUrl;
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToMany
    @JoinTable(
            name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )

    private Set<Category> categories = new LinkedHashSet<>();

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime now) {
        this.date = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
