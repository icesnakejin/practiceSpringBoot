package com.example.practiceExample.models;

public class Question {
    private final long id;
    private final String content;

    public Question(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
