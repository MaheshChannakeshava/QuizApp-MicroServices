package com.mahi.quiz_service.config;

import com.mahi.quiz_service.model.QuestionWrapper;
import com.mahi.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String difficultyLevel, @RequestParam Integer numQ);

    //TO Proivde question for the provided ID's to the Quiz application
    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> id);

    //TO provide the Quiz score for the Quiz Application
    @PostMapping("questions/getScore")
    public  ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
