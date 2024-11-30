package com.example.fdwproject2.service;

import com.example.fdwproject2.model.Challenge;
import com.example.fdwproject2.model.GameSession;
import com.example.fdwproject2.model.Submission;
import com.example.fdwproject2.model.enums.ChallengeType;
import com.example.fdwproject2.model.enums.GameStatus;
import com.example.fdwproject2.repository.ChallengeRepository;
import com.example.fdwproject2.repository.GameSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    private List<Challenge> currentGameChallenges;
    private int currentChallengeIndex = 0;
    public Challenge getCurrentChallenge() {
        GameSession session = gameSessionRepository.getCurrentSession();
        if (session == null || session.getStatus() != GameStatus.IN_PROGRESS) {
            return null;
        }
        return session.getCurrentChallenge();
    }

    public Challenge getNextChallenge() {
        if (currentGameChallenges == null || currentChallengeIndex >= currentGameChallenges.size()) {
            initializeChallenges();
        }
        return currentGameChallenges.get(currentChallengeIndex++);
    }

    private void initializeChallenges() {
        currentGameChallenges = new ArrayList<>();
        currentGameChallenges.add(createArrayChallenge());
        currentGameChallenges.add(createSQLChallenge());
        currentGameChallenges.add(createTheoryChallenge());
        currentChallengeIndex = 0;
    }

    private Challenge createArrayChallenge() {
        Challenge challenge = new Challenge();
        challenge.setType(ChallengeType.ARRAY);
        challenge.setQuestion("Scrie o funcție care găsește elementul maxim dintr-un array");
        challenge.setCorrectAnswer("return Arrays.stream(arr).max().getAsInt();");
        challenge.setTimeLimit(120);
        challenge.setPoints(100);
        return challenge;
    }

    private Challenge createSQLChallenge() {
        Challenge challenge = new Challenge();
        challenge.setType(ChallengeType.SQL);
        challenge.setQuestion("Scrie un query SQL pentru a găsi top 3 cele mai vândute produse");
        challenge.setCorrectAnswer("SELECT product_id, COUNT(*) as sales FROM orders GROUP BY product_id ORDER BY sales DESC LIMIT 3");
        challenge.setTimeLimit(120);
        challenge.setPoints(100);
        return challenge;
    }

    private Challenge createTheoryChallenge() {
        Challenge challenge = new Challenge();
        challenge.setType(ChallengeType.THEORY);
        challenge.setQuestion("Ce este polimorfismul în OOP?");
        challenge.setCorrectAnswer("Polimorfismul este capacitatea unui obiect de a lua multe forme");
        challenge.setTimeLimit(60);
        challenge.setPoints(50);
        return challenge;
    }

    public boolean verifySubmission(Submission submission) {
        if (submission == null || submission.getAnswer() == null) {
            return false;
        }

        GameSession session = gameSessionRepository.getCurrentSession();
        if (session == null || session.getStatus() != GameStatus.IN_PROGRESS) {
            return false;
        }

        Challenge currentChallenge = session.getCurrentChallenge();
        if (currentChallenge == null) {
            return false;
        }

        switch (currentChallenge.getType()) {
            case ARRAY:
                return verifyArraySolution(submission.getAnswer(), currentChallenge);
            case SQL:
                return verifySQLQuery(submission.getAnswer(), currentChallenge);
            case THEORY:
                return verifyTheoryAnswer(submission.getAnswer(), currentChallenge);
            default:
                return false;
        }
    }

    private boolean verifyArraySolution(String userAnswer, Challenge challenge) {
        if (userAnswer == null || challenge == null) {
            return false;
        }
        return userAnswer.trim().equals(challenge.getCorrectAnswer().trim());
    }

    private boolean verifySQLQuery(String userAnswer, Challenge challenge) {
        if (userAnswer == null || challenge == null) {
            return false;
        }
        return userAnswer.trim().equalsIgnoreCase(challenge.getCorrectAnswer().trim());
    }

    private boolean verifyTheoryAnswer(String userAnswer, Challenge challenge) {
        if (userAnswer == null || challenge == null) {
            return false;
        }
        String normalizedUserAnswer = userAnswer.toLowerCase().trim();
        String normalizedCorrectAnswer = challenge.getCorrectAnswer().toLowerCase().trim();

        return normalizedUserAnswer.contains(normalizedCorrectAnswer) ||
                normalizedCorrectAnswer.contains(normalizedUserAnswer);
    }

    // Metodă pentru resetarea provocărilor
    public void resetChallenges() {
        currentGameChallenges = null;
        currentChallengeIndex = 0;
    }
}