package com.quizplatform.quiz_platform.service;

import com.quizplatform.quiz_platform.entity.Question;
import com.quizplatform.quiz_platform.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> findByCategoryId(UUID categoryId) {
        return questionRepository.findByCategoryId(categoryId);
    }

    public List<Question> getRandomQuestions(UUID categoryId, int amount) {
        List<Question> questions = questionRepository.findByCategoryId(categoryId);
        Collections.shuffle(questions);
        return questions.stream()
                .limit(amount)
                .toList();
    }

    public Question create(Question question) {
        return questionRepository.save(question);
    }
}
