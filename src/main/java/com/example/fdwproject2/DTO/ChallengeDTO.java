package com.example.fdwproject2.DTO;

import com.example.fdwproject2.model.enums.ChallengeType;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class ChallengeDTO {
    private Long id;
    private String question;
    private ChallengeType type;
    private Integer timeLimit;
    private Integer points;

    public ChallengeDTO() {
    }

    public ChallengeDTO(Long id, String question, ChallengeType type, Integer timeLimit, Integer points) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.timeLimit = timeLimit;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ChallengeType getType() {
        return type;
    }

    public void setType(ChallengeType type) {
        this.type = type;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}