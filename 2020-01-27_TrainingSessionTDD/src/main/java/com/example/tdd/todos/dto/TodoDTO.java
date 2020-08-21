package com.example.tdd.todos.dto;

import lombok.Data;

@Data
public class TodoDTO {
    private long id;
    private boolean valid;
    private String text;
}
