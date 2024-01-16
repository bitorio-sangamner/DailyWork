package com.quiz.impl;

import com.quiz.Entities.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionClient questionClient;
    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> get() {
        List<Quiz> quizzes=quizRepository.findAll();

        List<Quiz> newQizList=quizzes.stream().map(quiz-> {

            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
            return quiz;


        }).collect(Collectors.toList());
        return newQizList;
    }

    @Override
    public Quiz get(Long id) {

        Quiz quiz=quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz not found"));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
        return quiz;
    }
}
