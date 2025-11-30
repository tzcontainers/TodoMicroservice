package com.todo.microservice.todo.model;

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