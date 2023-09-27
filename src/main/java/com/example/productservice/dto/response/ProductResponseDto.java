package com.example.productservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponseDto {

    private String id;
    private String title;
    private String imageUrl;
    private String category;
    private double price;
    private String currency;
}
