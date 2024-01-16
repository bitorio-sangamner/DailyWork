package com.question.QuestionService.repository;

import com.question.QuestionService.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    //custom find method
    List<Question> findByQuizId(Long quizId);
}
