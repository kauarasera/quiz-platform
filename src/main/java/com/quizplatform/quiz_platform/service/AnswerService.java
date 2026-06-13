package com.quizplatform.quiz_platform.service;

import com.quizplatform.quiz_platform.entity.Answer;
import com.quizplatform.quiz_platform.entity.Question;
import com.quizplatform.quiz_platform.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer create(Answer answer) {
        if (answerRepository.existsByTextAndQuestionId(
                answer.getText(),
                answer.getQuestion().getId())) {
            throw new RuntimeException("Answer already exists for this question");
        }

        List<Answer> existing = answerRepository
                .findByQuestionId(answer.getQuestion().getId());
        if (existing.size() >= 4) {
            throw new RuntimeException(
                    "Question already has 4 answers");
        }

        return answerRepository.save(answer);
    }
}
