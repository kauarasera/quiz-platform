package com.quizplatform.quiz_platform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class QuestionRequestDto {

    @NotBlank(message = "Question text is required")
    private String text;

    @NotNull(message = "Category is required")
    private UUID categoryId;
}