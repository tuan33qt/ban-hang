package com.tuan.banhang.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int product_id;
    private int cost_price;
    private int current_quantity;
    private String description;
    private String imageName;
    private String name;
    private int categoryId;
}
