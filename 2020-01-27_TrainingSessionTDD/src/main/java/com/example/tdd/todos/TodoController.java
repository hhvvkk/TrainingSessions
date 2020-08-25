package com.example.tdd.todos;


import com.example.tdd.todos.dto.TodoDTO;
import com.example.tdd.todos.entity.Todo;
import com.example.tdd.todos.mapper.TodoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

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
        Todo todo = todoService.save(todoMapper.toEntity(todoDTO));
        return todoMapper.toDTO(todo);
    }

    @GetMapping
    public List<TodoDTO> findAllTodos() {
        List<Todo> todos = todoService.findAll();
        return todoMapper.toDTOsList(todos);
    }

    @GetMapping("{id}")
    public TodoDTO find(@PathVariable("id") Long id) {
        return todoMapper.toDTO(todoService.find(id));
    }
}
