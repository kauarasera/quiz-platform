package com.quizplatform.quiz_platform.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDto {
    private UUID id;
    private String text;
    @JsonProperty("isCorrect")
    private boolean isCorrect;
    private UUID questionId;
}
