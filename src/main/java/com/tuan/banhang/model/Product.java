package com.tuan.banhang.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int product_id;
    @Column(name = "cost_price")
    private int cost_price;
    @Column(name = "current_quantity")
    private int current_quantity;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String imageName;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private category categoryId;
}
