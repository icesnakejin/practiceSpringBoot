package com.example.practiceExample.services;

import com.example.practiceExample.models.Answer;
import com.example.practiceExample.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository repository;

    public AnswerService() {
        repository = new AnswerRepository();
    }

    public Long addAnswer(long questionId, String content) {

        return repository.postAnswer(questionId, content);
    }

    public boolean upvotes(long questionId, long answerId) {
        return repository.upvote(questionId, answerId);
    }

//    public Optional<Integer> getUpvotes(long questionId, long answerId) {
//        return repository.getUpvotes(questionId, answerId);
//    }
    public Optional<Answer> getAnswer(long questionId, long answerId) {
        return repository.getAnswer(questionId, answerId);
    }
}
