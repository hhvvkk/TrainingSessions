package com.example.tdd.todos;


import com.example.tdd.todos.dto.TodoDTO;
import com.example.tdd.todos.entity.Todo;
import com.example.tdd.todos.mapper.TodoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("todo")
public class TodoController {

    private final TodoService todoService;

    private final TodoMapper todoMapper;

    @Autowired
    public TodoController(TodoService todoService,
                          TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @PostMapping
    public TodoDTO create(@RequestBody TodoDTO todoDTO) {
        Todo todo = todoService.create(todoMapper.toEntity(todoDTO));
        return todoMapper.toDTO(todo);
    }

}