package com.quizplatform.quiz_platform.controller;

import com.quizplatform.quiz_platform.dto.AnswerResponseDto;
import com.quizplatform.quiz_platform.entity.Answer;
import com.quizplatform.quiz_platform.entity.Question;
import com.quizplatform.quiz_platform.repository.AnswerRepository;
import com.quizplatform.quiz_platform.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerRepository answerRepository;
    private final AnswerService answerService;

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<AnswerResponseDto>> findByQuestionId(
            @PathVariable UUID questionId) {
        List<AnswerResponseDto> answers = answerRepository
                .findByQuestionId(questionId)
                .stream()
                .map(this::toDto)
                .toList();
        return ResponseEntity.ok(answers);
    }

    private AnswerResponseDto toDto(Answer answer) {
        AnswerResponseDto dto = new AnswerResponseDto();
        dto.setId(answer.getId());
        dto.setText(answer.getText());
        dto.setCorrect(answer.isCorrect());
        dto.setQuestionId(answer.getQuestion().getId());
        return dto;
    }

    @PostMapping
    public ResponseEntity<AnswerResponseDto> create(
            @RequestBody AnswerResponseDto requestDto) {
        Answer answer = new Answer();
        answer.setText(requestDto.getText());
        answer.setCorrect(requestDto.isCorrect());

        Question question = new Question();
        question.setId(requestDto.getQuestionId());
        answer.setQuestion(question);

        Answer saved = answerRepository.save(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
        answerRepository.delete(answer);
        return ResponseEntity.noContent().build();
    }
}