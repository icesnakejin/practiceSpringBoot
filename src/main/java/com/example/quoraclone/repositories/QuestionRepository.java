package com.example.quoraclone.repositories;

import com.example.quoraclone.models.Question;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class QuestionRepository {
    private final ConcurrentHashMap<Long, Question> questions;
    private final AtomicLong index;
    public QuestionRepository() {
        questions = new ConcurrentHashMap<>();
        index = new AtomicLong(1);
    }

    public long postQuestion(String content) {
        long indexValue = index.getAndIncrement();
        Question newQuestion = new Question(indexValue, content);
        questions.put(indexValue, newQuestion);
        return indexValue;
    }

    public Optional<Question> getQuestion(long questionId) {
        if (!questions.containsKey(questionId)) {
            return Optional.empty();
        } else {
            return Optional.of(questions.get(questionId));
        }
    }
}
