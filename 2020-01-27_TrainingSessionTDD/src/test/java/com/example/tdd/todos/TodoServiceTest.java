package com.example.tdd.todos;

import com.example.tdd.todos.entity.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

//Contract testing
// OLD ONE WAS @RunsWith(MockitoRunner.class)
@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private static TodoRepository todoRepository;

    @InjectMocks
    private static TodoService todoService;

    @BeforeEach
    public void init() {
        Mockito
                .lenient()
                .when(todoRepository.save(Mockito.any(Todo.class)))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    public void shouldReturnValidTodoWhenTextIsValid() {
        Todo todo = new Todo();
        todo.setText("My text here");
        todo = todoService.save(todo);
        Assertions.assertTrue(todo.isValid());
    }

    @Test
    public void shouldReturnInValidTodoWhenTextIsInValidForSingleBracket() {
        Todo todo = new Todo();
        todo.setText("My text here 'this is invalid");
        todo = todoService.save(todo);
        Assertions.assertFalse(todo.isValid());
    }

    @Test
    public void shouldReturnValidTodoWhenTextIsInValidForDoubleBracket() {
        Todo todo = new Todo();
        todo.setText("My text here 'this is invalid'");
        todo = todoService.save(todo);
        Assertions.assertTrue(todo.isValid());
    }

    @Test
    public void shouldThrowExceptionOnEmptyCurrency() {
        Todo emptyTodoText = new Todo();
        Assertions.assertThrows(ResponseStatusException.class, () -> todoService.save(emptyTodoText));
    }
}
