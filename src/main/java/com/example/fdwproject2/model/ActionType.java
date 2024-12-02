package com.example.fdwproject2.model;

import lombok.Getter;

@Getter
public enum ActionType {
    LOGIN("Login"),
    LOGOUT("Logout"),
    REGISTER("Register"),
    ROLE_CHANGE("Role Change"),
    FAILED_ATTEMPT("Failed Attempt");

    private final String displayName;

    ActionType(String displayName) {
        this.displayName = displayName;
    }
}