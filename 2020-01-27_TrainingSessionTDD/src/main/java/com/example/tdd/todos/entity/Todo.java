package com.example.tdd.todos.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "valid", nullable = false)
    private boolean valid;

    @Column(name = "text", nullable =  false, length = 1000)
    private String text;

}
