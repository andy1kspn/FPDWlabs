package com.example.fdwproject2.DTO;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class PlayerDTO {
    private Long id;
    private Integer score;
    private boolean isReady;

    public PlayerDTO() {
    }

    public PlayerDTO(Long id, Integer score, boolean isReady) {
        this.id = id;
        this.score = score;
        this.isReady = isReady;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}