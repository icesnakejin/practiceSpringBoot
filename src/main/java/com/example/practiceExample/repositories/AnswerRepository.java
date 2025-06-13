package com.example.practiceExample.repositories;

import com.example.practiceExample.models.Answer;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AnswerRepository {
    private final ConcurrentHashMap<Long, ConcurrentHashMap<Long, Answer>> answers;
    private final ConcurrentHashMap<Long, AtomicLong> indexes;
    public AnswerRepository() {
        answers = new ConcurrentHashMap<>();
        indexes = new ConcurrentHashMap<>();
    }

    public long postAnswer(Long questionId, String content) {
        AtomicLong indexValue = indexes.computeIfAbsent(questionId, k -> new AtomicLong(1));
        long currentIndex = indexValue.get();
        Answer newAnswer = new Answer(indexValue.getAndIncrement(), questionId, content);
        answers.computeIfAbsent(questionId, qKey -> new ConcurrentHashMap<>())
                .put(newAnswer.getId(), newAnswer);
        return currentIndex;
    }

    public boolean upvote(long questionId, long answerId) {
        if (!answers.containsKey(questionId)) {
            return false;
        }

        ConcurrentHashMap<Long, Answer> answerMap = answers.get(questionId);
        if (!answerMap.containsKey(answerId)) {
            return false;
        }
        answerMap.get(answerId).incrementUpvotes();
        return true;
    }

    public Optional<Integer> getUpvotes(long questionId, long answerId) {
        if (!answers.containsKey(questionId)) {
            return Optional.empty();
        }

        ConcurrentHashMap<Long, Answer> answerMap = answers.get(questionId);
        if (!answerMap.containsKey(answerId)) {
            return Optional.empty();
        }

        return Optional.of(answerMap.get(answerId).getUpvotes());
    }


    public Optional<Answer> getAnswer(long questionId, long answerId) {
        if (!answers.containsKey(questionId)) {
            return Optional.empty();
        }

        ConcurrentHashMap<Long, Answer> answerMap = answers.get(questionId);
        if (!answerMap.containsKey(answerId)) {
            return Optional.empty();
        }

        return Optional.of(answerMap.get(answerId));
    }
}
