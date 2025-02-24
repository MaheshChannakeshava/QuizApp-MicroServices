package com.mahi.question_service.controller;


import com.mahi.question_service.model.QuestionWrapper;
import com.mahi.question_service.model.Questions;
import com.mahi.question_service.model.Response;
import com.mahi.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestion")
    public ResponseEntity<List<Questions>> getAllQuestions(){

        return questionService.getAllQuestions();
    }

    @GetMapping("category/{difficultyLevel}")
    public List<Questions> getQuestionByLevel(@PathVariable String difficultyLevel){
        return questionService.getQuestionByLevel(difficultyLevel);
    }

    @PostMapping("add")
    public ResponseEntity<String> createQuestion(@RequestBody Questions questions){
        return questionService.createQuestion(questions);
    }

    //For MicroService Application for generating questions for QUIZ Service

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String difficultyLevel, @RequestParam Integer numQ){
        return questionService.getQuestionsForQuiz(difficultyLevel, numQ);
    }

    //TO Proivde question for the provided ID's to the Quiz application
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> id){
        return questionService.getQuestionFromId(id);
    }

    //TO provide the Quiz score for the Quiz Application
    @PostMapping("getScore")
    public  ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }


}
