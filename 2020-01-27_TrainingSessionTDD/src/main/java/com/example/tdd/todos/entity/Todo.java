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
    private boolean value;

    @Column(name = "text", nullable =  false, length = 500)
    private String englishNumberName;

}
