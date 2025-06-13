package com.example.quoraclone.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Answer {
    private final long id;
    private final long questionId;
    private final String content;
    private final AtomicInteger upvotes;

    public Answer(long id, long questionId, String content) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        upvotes = new AtomicInteger(0);
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public long getQuestionId() {
        return questionId;
    }

    public int getUpvotes() {
        return upvotes.get();
    }

    public void incrementUpvotes() {
        upvotes.incrementAndGet();
    }
}
