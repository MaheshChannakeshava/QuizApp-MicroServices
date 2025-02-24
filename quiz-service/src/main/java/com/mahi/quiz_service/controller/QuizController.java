package com.mahi.quiz_service.controller;


import com.mahi.quiz_service.DTO.QuizDTO;
import com.mahi.quiz_service.model.QuestionWrapper;
import com.mahi.quiz_service.model.Response;
import com.mahi.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.getDifficultyLevel(),quizDTO.getNumQ(),quizDTO.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

}
