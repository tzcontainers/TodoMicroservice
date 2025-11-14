package com.todo.microservice.models;

public enum TodoPriority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    URGENT("Urgent");
    public final String value;


    TodoPriority(String value) {
        this.value = value;
    }
}