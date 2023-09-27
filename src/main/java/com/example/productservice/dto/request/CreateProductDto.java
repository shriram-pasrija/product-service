package com.example.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProductDto {

    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;
    private String currency;
}
