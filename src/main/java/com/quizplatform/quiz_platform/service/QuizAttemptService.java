package com.quizplatform.quiz_platform.service;

import com.quizplatform.quiz_platform.entity.QuizAttempt;
import com.quizplatform.quiz_platform.repository.QuizAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;

    public QuizAttempt save(QuizAttempt attempt) {
        return quizAttemptRepository.save(attempt);
    }

    public List<QuizAttempt> findByUserId(UUID userId) {
        return quizAttemptRepository.findByUserIdOrderByStartedAtDesc(userId);
    }

    public QuizAttempt findById(UUID id) {
        return quizAttemptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz attempt not found"));
    }
}