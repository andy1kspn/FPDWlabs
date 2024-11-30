package com.example.fdwproject2.DTO;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class SubmissionDTO {
    private Long playerId;
    private Long challengeId;
    private String answer;

    public SubmissionDTO() {
    }

    public SubmissionDTO(Long playerId, Long challengeId, String answer) {
        this.playerId = playerId;
        this.challengeId = challengeId;
        this.answer = answer;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}