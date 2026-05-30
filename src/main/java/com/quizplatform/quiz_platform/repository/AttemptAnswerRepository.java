package com.quizplatform.quiz_platform.repository;

import com.quizplatform.quiz_platform.entity.AttemptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttemptAnswerRepository extends JpaRepository<AttemptAnswer, UUID> {

    List<AttemptAnswer> findByAttemptId(UUID attemptId);
}
