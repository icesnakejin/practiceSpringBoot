package com.example.practiceExample.services;

import com.example.practiceExample.models.Question;
import com.example.practiceExample.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService() {
        repository = new QuestionRepository();
    }

    public Long addQuestion(String content) {
        return repository.postQuestion(content);
    }

    public Optional<Question> getQuestion(long questionId) {
        return repository.getQuestion(questionId);
    }
}
