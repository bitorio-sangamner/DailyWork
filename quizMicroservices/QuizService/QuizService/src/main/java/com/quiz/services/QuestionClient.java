package com.quiz.services;

import com.quiz.Entities.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(url="http://localhost:9092",value="Question-Client")
@FeignClient(name="QUESTION-SERVICE")
public interface QuestionClient {

    //

    @GetMapping("/question/quiz/{quizId}")
    List<Question> getQuestionOfQuiz(@PathVariable Long quizId);

}
