package com.quiz.services;

import com.quiz.Entities.Quiz;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuizService {

    Quiz add(Quiz quiz);
    List<Quiz> get();
    Quiz get(Long id);
}
