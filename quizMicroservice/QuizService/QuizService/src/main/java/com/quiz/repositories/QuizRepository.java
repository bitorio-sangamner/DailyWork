package com.quiz.repositories;


import com.quiz.Entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
