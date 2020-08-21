package com.example.tdd.todos;

import com.example.tdd.todos.entity.Todo;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(Todo entity) {
        if (Strings.isNullOrEmpty(entity.getText())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo text should be entered in");
        }
        return todoRepository.save(entity);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }
}
