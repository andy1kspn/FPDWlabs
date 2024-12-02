package com.example.fdwproject2.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DashboardDto {
    private List<LoginAttemptDto> loginAttempts;
    private String username;
    private String role;

    public DashboardDto() {
    }

    public DashboardDto(List<LoginAttemptDto> loginAttempts, String username, String role) {
        this.loginAttempts = loginAttempts;
        this.username = username;
        this.role = role;
    }

    public List<LoginAttemptDto> getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(List<LoginAttemptDto> loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}