package com.mahi.question_service.Repository;


import com.mahi.question_service.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Questions, Integer> {
    List<Questions> findByDifficultyLevel(String difficultyLevel);

    @Query(value = "Select q.id from question q Where q.difficulty_level=:difficultyLevel " +
            "Order By RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomNumByDifficultyLevel(String difficultyLevel, int numQ);
}
