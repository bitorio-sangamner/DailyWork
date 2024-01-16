package com.quiz.controller;

import com.quiz.Entities.Quiz;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionClient questionClient;

    //create
    @PostMapping
    public Quiz create(@RequestBody Quiz quiz)
    {
        return quizService.add(quiz);
    }

    //get all
    @GetMapping
    public List<Quiz> get()
    {
       return quizService.get();
    }

    @GetMapping("/{id}")
    public Quiz getOne(@PathVariable Long id)
    {
        return quizService.get(id);
    }
}
