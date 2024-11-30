package com.example.fdwproject2.controller;

import com.example.fdwproject2.model.Challenge;
import com.example.fdwproject2.model.Submission;
import com.example.fdwproject2.service.ChallengeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/verify")
    public boolean verifyAnswer(@RequestBody Submission submission) {
        return challengeService.verifySubmission(submission);
    }

    @GetMapping("/current")
    public Challenge getCurrentChallenge() {
        return challengeService.getCurrentChallenge();
    }
}
