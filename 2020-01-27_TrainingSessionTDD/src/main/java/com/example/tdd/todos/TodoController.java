package com.example.tdd.todos;


import com.example.tdd.currency.CurrencyService;
import com.example.tdd.currency.mapper.CurrencyMapper;
import com.example.tdd.todos.mapper.TodoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("currency")
public class TodoController {


    private final TodoService todoService;

    private final TodoMapper todoMapper;

    @Autowired
    public TodoController(TodoService todoService,
                          TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

}
