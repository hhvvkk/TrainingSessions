package com.example.tdd.todos.mapper;

import com.example.tdd.todos.dto.TodoDTO;
import com.example.tdd.todos.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TodoMapper {

    public abstract Todo toEntity(TodoDTO dto);

    public abstract TodoDTO toDTO(Todo entity);

    public List<TodoDTO> toDTOsList(List<Todo> entities) {
        return entities
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
