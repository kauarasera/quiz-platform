package com.quizplatform.quiz_platform.controller;

import com.quizplatform.quiz_platform.dto.CategoryDto;
import com.quizplatform.quiz_platform.dto.CategoryRequestDto;
import com.quizplatform.quiz_platform.entity.Category;
import com.quizplatform.quiz_platform.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        List<CategoryDto> categories = categoryService.findAll()
                .stream()
                .map(this::toDto)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable UUID id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(toDto(category));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(
            @Valid @RequestBody CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.getName());
        category.setDescription(requestDto.getDescription());
        Category saved = categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}