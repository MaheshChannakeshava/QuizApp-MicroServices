package com.mahi.quiz_service.service;


import com.mahi.quiz_service.Repository.QuizRepo;
import com.mahi.quiz_service.config.QuizInterface;
import com.mahi.quiz_service.model.QuestionWrapper;
import com.mahi.quiz_service.model.Quiz;
import com.mahi.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String difficultyLevel, int numQ, String title) {
         List<Integer> questions= quizInterface.getQuestionsForQuiz(difficultyLevel, numQ).getBody();
         Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Integer> questionIds= quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questionWrappers = quizInterface.getQuestionsFromId(questionIds);


        return questionWrappers;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score= quizInterface.getScore(responses);
        return score;
    }
}
