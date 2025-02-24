package com.mahi.question_service.service;


import com.mahi.question_service.Repository.QuestionRepo;
import com.mahi.question_service.model.QuestionWrapper;
import com.mahi.question_service.model.Questions;
import com.mahi.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public List<Questions> getQuestionByLevel(String difficultyLevel) {
        return questionRepo.findByDifficultyLevel(difficultyLevel);
    }

    public ResponseEntity<String> createQuestion(Questions questions) {
        try {
            questionRepo.save(questions);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (String difficultyLevel, Integer numQ) {

        List<Integer> questions = questionRepo.findRandomNumByDifficultyLevel(difficultyLevel, numQ);

        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> id) {

        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Questions> questionsList = new ArrayList<>();

        for (Integer i : id) {
            questionsList.add(questionRepo.findById(i).get());
        }

        for (Questions questions : questionsList) {
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(questions.getId());
            questionWrapper.setQuestionTitle(questions.getQuestionTitle());
            questionWrapper.setOption1(questions.getOption1());
            questionWrapper.setOption2(questions.getOption2());
            questionWrapper.setOption3(questions.getOption3());
            questionWrapper.setOption4(questions.getOption4());

            wrappers.add(questionWrapper);

        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int rightAnswer = 0;
        for (Response res : responses) {
            Questions questions = questionRepo.findById(res.getId()).get();
            if (res.getResponse().equals(questions.getAnswer()))
                rightAnswer++;
        }
        return new ResponseEntity<>(rightAnswer, HttpStatus.OK);
    }
}
