package com.example.productservice.service;

import com.example.productservice.dto.request.CreateProductDto;
import com.example.productservice.dto.request.UpdateProductDto;
import com.example.productservice.dto.response.ProductResponseDto;
import com.example.productservice.entities.Category;
import com.example.productservice.entities.Product;
import com.example.productservice.exceptions.BadRequestException;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<ProductResponseDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(this::getProductResponseDto).toList();
    }

    public ProductResponseDto getProductById(String productId) {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(productId));

        if (optionalProduct.isEmpty()) {
            throw new NotFoundException("Product with id " + productId + "  not found");
        }

        Product product = optionalProduct.get();

        return getProductResponseDto(product);
    }

    public ProductResponseDto createProduct(CreateProductDto request) {
        Product product = new Product();
        product.setPrice(request.getPrice());
        product.setCurrency(request.getCurrency());

        Category category;
        try {
            category = categoryService.getCategoryByName(request.getCategory());
        } catch (NotFoundException e) {
            category = new Category();
            category.setName(request.getCategory());
        }

        product.setCategory(category);

        product.setTitle(request.getTitle());
        product.setImageUrl(request.getImageUrl());
        product.setDescription(request.getDescription());

        Product savedProduct = productRepository.save(product);

        return getProductResponseDto(savedProduct);
    }

    public ProductResponseDto updateProduct(UpdateProductDto request) {

        if (request.getId() == null || request.getId().isEmpty()) throw new BadRequestException("Id is missing");

        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(request.getId()));

        if (optionalProduct.isEmpty()) throw new NotFoundException("Product not found");

        Product product = optionalProduct.get();

        product.setPrice(request.getPrice());
        product.setCurrency(request.getCurrency());

        Category category;
        try {
            category = categoryService.getCategoryByName(request.getCategory());
        } catch (NotFoundException e) {
            category = new Category();
            category.setName(request.getCategory());
        }

        product.setCategory(category);

        product.setTitle(request.getTitle());
        product.setImageUrl(request.getImageUrl());
        product.setDescription(request.getDescription());

        Product savedProduct = productRepository.save(product);

        return getProductResponseDto(savedProduct);
    }

    public List<ProductResponseDto> getProductsByCategoryName(String categoryName) throws NotFoundException {
        Category category = categoryService.getCategoryByName(categoryName);
        return category.getProducts().stream().map(this::getProductResponseDto).toList();
    }

    public void deleteProduct(String productId) {
        if (productId == null || productId.isEmpty()) throw new BadRequestException("Id is missing");
        UUID productUUID;
        try {
            productUUID = UUID.fromString(productId);
        } catch (Exception e) {
            throw new BadRequestException("Malformed ID");
        }
        productRepository.deleteById(productUUID);
    }

    private ProductResponseDto getProductResponseDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getUuid().toString());
        dto.setCategory(product.getCategory().getName());
        dto.setTitle(product.getTitle());
        dto.setImageUrl(product.getImageUrl());
        dto.setPrice(product.getPrice());
        dto.setCurrency(product.getCurrency());
        return dto;
    }
}
