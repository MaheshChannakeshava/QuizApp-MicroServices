package com.mahi.quiz_service.DTO;

import lombok.Data;

@Data
public class QuizDTO {

    private String difficultyLevel;
    private Integer numQ;
    private String title;
}
