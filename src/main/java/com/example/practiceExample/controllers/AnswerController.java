package com.example.practiceExample.controllers;

import com.example.practiceExample.services.AnswerService;
import com.example.practiceExample.models.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("answers/")
public class AnswerController {
    private static final Logger log = LoggerFactory.getLogger(AnswerController.class);
    AnswerService service = new AnswerService();

    @PostMapping("add")
    public Long addAnswer(@RequestParam long questionId, @RequestParam String content) {
        long answerIdCreated = service.addAnswer(questionId, content);
        log.info("answer created with id: {}", answerIdCreated);
        return answerIdCreated;
    }

    @PutMapping("upvote")
    public boolean upvote(@RequestParam long questionId, @RequestParam Long answerId) {
        boolean success = service.upvotes(questionId, answerId);
        if (!success) {
            log.info("failed upvoting id: {}", answerId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return true;
    }

    @GetMapping("get")
    @Nullable
    public Answer getAnswer(@RequestParam Long questionId, @RequestParam Long answerId) {
        return service.getAnswer(questionId, answerId).orElseThrow(
                () -> {
                    log.error("question not found with questionId: {} and answerId: {}", questionId, answerId);
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "answer not found"
                    );
                }
        );
    }
}
