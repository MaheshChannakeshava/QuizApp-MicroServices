package com.mahi.question_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "question")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String difficultyLevel;
}
