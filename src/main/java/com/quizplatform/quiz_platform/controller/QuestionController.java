package com.quizplatform.quiz_platform.controller;

import com.quizplatform.quiz_platform.dto.QuestionRequestDto;
import com.quizplatform.quiz_platform.dto.QuestionResponseDto;
import com.quizplatform.quiz_platform.entity.Category;
import com.quizplatform.quiz_platform.entity.Question;
import com.quizplatform.quiz_platform.service.CategoryService;
import com.quizplatform.quiz_platform.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<QuestionResponseDto> create(
            @Valid @RequestBody QuestionRequestDto requestDto) {
        Category category = categoryService.findById(requestDto.getCategoryId());
        Question question = new Question();
        question.setText(requestDto.getText());
        question.setCategory(category);
        Question saved = questionService.create(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<QuestionResponseDto>> findByCategoryId(
            @PathVariable UUID categoryId) {
        List<QuestionResponseDto> questions = questionService
                .findByCategoryId(categoryId)
                .stream()
                .map(this::toDto)
                .toList();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> findById(
            @PathVariable UUID id) {
        Question question = questionService.findById(id);
        return ResponseEntity.ok(toDto(question));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        questionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private QuestionResponseDto toDto(Question question) {
        QuestionResponseDto dto = new QuestionResponseDto();
        dto.setId(question.getId());
        dto.setText(question.getText());
        dto.setOrderNumber(question.getOrderNumber());
        dto.setCategoryId(question.getCategory().getId());
        dto.setCategoryName(question.getCategory().getName());
        return dto;
    }
}
