package com.example.quoraclone.controllers;

import com.example.quoraclone.services.QuestionService;
import com.example.quoraclone.models.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("questions/")
public class QuestionController {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
    QuestionService questionService = new QuestionService();

    @PostMapping("add")
    public Long addQuestion(@RequestParam String content) {
        long questionIdCreated = questionService.addQuestion(content);
        log.info("question created with id: {}", questionIdCreated);
        return questionIdCreated;
    }

    @GetMapping("get/{questionId}")
    @Nullable
    public Question getQuestion(@PathVariable Long questionId) {
        return questionService.getQuestion(questionId).orElseThrow(
                () -> {
                    log.error("question not found with id: {}", questionId);
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "question not found"
                    );
                }
        );
    }
}
