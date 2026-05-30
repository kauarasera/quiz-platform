package com.quizplatform.quiz_platform.service;

import com.quizplatform.quiz_platform.entity.Category;
import com.quizplatform.quiz_platform.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category create(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category name already exists");
        }
        return categoryRepository.save(category);
    }

    public void delete(UUID id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}
