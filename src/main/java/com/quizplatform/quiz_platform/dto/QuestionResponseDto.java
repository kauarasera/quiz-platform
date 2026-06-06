package com.quizplatform.quiz_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDto {
    private UUID id;
    private String text;
    private Integer orderNumber;
    private UUID categoryId;
    private String categoryName;
}
