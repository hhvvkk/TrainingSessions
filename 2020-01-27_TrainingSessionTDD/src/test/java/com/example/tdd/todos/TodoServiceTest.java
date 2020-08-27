package com.example.tdd.todos;

import com.example.tdd.todos.entity.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

//Contract testing
// OLD ONE WAS @RunsWith(MockitoRunner.class)
@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    /**
     *     @Mock
     *     private static TodoRepository todoRepository;
     *
     *     @InjectMocks
     *     private static TodoService todoService;
     */

    private static final TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
    private static final TodoService todoService = new TodoService(todoRepository);

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
        todo.setText(PunctuationTest.VALID_STRINGS.get(0));
        todo = todoService.save(todo);
        Assertions.assertTrue(todo.isValid());
    }

    @Test
    public void shouldReturnInValidTodoWhenTextIsInValid() {
        Todo todo = new Todo();
        todo.setText(PunctuationTest.IN_VALID_STRINGS.get(0));
        todo = todoService.save(todo);
        Assertions.assertFalse(todo.isValid());
    }

    @Test
    public void shouldSaveTodoWhenSaveFunctionCalled() {
        Todo todo = new Todo();
        todo.setText("My text here 'this is invalid'");
        todo = todoService.save(todo);
        Mockito.verify(todoRepository, Mockito.atLeast(1)).save(Mockito.any(Todo.class));
        //careful with verify - this will verify across tests - since we use the same Mock
    }

    @Test
    public void shouldThrowExceptionOnEmptyTodoText() {
        Todo emptyTodoText = new Todo();
        Assertions.assertThrows(ResponseStatusException.class, () -> todoService.save(emptyTodoText));
    }

    @Test
    public void shouldThrowExceptionOnFindNullIdTodo() {
        Assertions.assertThrows(ResponseStatusException.class, () -> todoService.find(null));
    }

    @Test
    public void shouldThrowExceptionOnFindInvalidIdTodo() {
        Assertions.assertThrows(ResponseStatusException.class, () -> todoService.find((long) -1));
    }
}
