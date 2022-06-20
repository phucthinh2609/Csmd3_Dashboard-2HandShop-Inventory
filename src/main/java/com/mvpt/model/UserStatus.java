package com.mvpt.model;

public enum UserStatus {
    ACTIVE("ACTIVE"),

    BLOCK("BLOCK");

    private String value;

    private UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static UserStatus parseUserStatus(String value) {
        UserStatus[] values = values();
        for (UserStatus status : values) {
            if (status.value.equals(value))
                return status;
        }
        throw new IllegalArgumentException("invalid");
    }
}
