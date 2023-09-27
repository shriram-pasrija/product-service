package com.example.productservice.service;

import com.example.productservice.entities.Category;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<String> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getName).toList();
    }

    public Category getCategoryByName(String categoryName) throws NotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);

        if (optionalCategory.isEmpty()) throw new NotFoundException("Category does not exists");

        return optionalCategory.get();
    }
}
