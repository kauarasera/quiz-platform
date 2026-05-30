package com.quizplatform.quiz_platform.repository;

import com.quizplatform.quiz_platform.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    List<Answer> findByQuestionId(UUID questionId);
}
