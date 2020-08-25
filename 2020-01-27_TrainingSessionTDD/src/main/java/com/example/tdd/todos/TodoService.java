package com.example.tdd.todos;

import com.example.tdd.todos.entity.Todo;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo save(Todo entity) {
        if (Strings.isNullOrEmpty(entity.getText())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo text should be entered in");
        }

        //TODO can be moved to entity itself
        entity.setValid(Punctuation.wellFormed(entity.getText()));

        return todoRepository.save(entity);
    }

    public Todo find(Long id) {
        if (Objects.isNull(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo can't be found with null id");
        }

        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Todo not found by id [%d]", id)));
    }
}
