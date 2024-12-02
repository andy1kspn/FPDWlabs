package com.example.fdwproject2.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginAttemptDto {
    private LocalDateTime timestamp;
    private String ipAddress;
    private String action;
    private boolean successful;

    public LoginAttemptDto() {
    }

    public LoginAttemptDto(LocalDateTime timestamp, String ipAddress, String action, boolean successful) {
        this.timestamp = timestamp;
        this.ipAddress = ipAddress;
        this.action = action;
        this.successful = successful;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}