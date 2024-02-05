package com.question.QuestionService.services;

import com.question.QuestionService.entities.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionService {

    Question create(Question questionObj);
    List<Question> get();

    Question getOne(Long id);

    List<Question> getQuestionsOfQuiz(Long quizId);
}
