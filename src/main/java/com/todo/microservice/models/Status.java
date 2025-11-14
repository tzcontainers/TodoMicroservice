package com.todo.microservice.models;

public enum Status {
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    public final String value;

    Status(String value) {
        this.value = value;
    }
}