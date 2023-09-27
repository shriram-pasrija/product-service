package com.example.productservice.controllers;

import com.example.productservice.dto.request.CreateProductDto;
import com.example.productservice.dto.request.UpdateProductDto;
import com.example.productservice.dto.response.ProductResponseDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getProduct(@PathVariable("productId") String productId) throws NotFoundException {
        return productService.getProductById(productId);
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody CreateProductDto createProductDto) {
        return productService.createProduct(createProductDto);
    }

    @GetMapping("category/{categoryName}")
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
        return productService.getProductsByCategoryName(categoryName);
    }

    @PutMapping
    public ProductResponseDto updateProduct(@RequestBody UpdateProductDto updateProductDto) throws NotFoundException {
        return productService.updateProduct(updateProductDto);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") String productId) {
        productService.deleteProduct(productId);
    }
}
