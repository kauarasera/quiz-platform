package com.quizplatform.quiz_platform.repository;

import com.quizplatform.quiz_platform.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    boolean existsByTextAndCategoryId(String text, UUID categoryId);

    List<Question> findByCategoryId(UUID categoryId);
}