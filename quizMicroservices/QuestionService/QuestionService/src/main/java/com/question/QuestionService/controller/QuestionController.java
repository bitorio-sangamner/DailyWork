package com.question.QuestionService.controller;

import com.question.QuestionService.entities.Question;
import com.question.QuestionService.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    //create
    @PostMapping
    public Question create(@RequestBody Question question)
    {
        return questionService.create(question);
    }

    //get all
    @GetMapping
    public List<Question> getAll()
    {
        return questionService.get();
    }

    //get one
    @GetMapping("/{questionId}")
    public Question getOne(@PathVariable Long questionId)
    {
       return questionService.getOne(questionId);
    }

    //get all questions of specific quiz
    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsOfQuiz(@PathVariable Long quizId)
    {
       return questionService.getQuestionsOfQuiz(quizId);
    }

}
