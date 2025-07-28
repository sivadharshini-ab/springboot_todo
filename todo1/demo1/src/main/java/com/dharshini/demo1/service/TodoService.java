package com.dharshini.demo1.service;

import com.dharshini.demo1.dto.TodoRequest;
import com.dharshini.demo1.dto.TodoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoService {
    Page<TodoResponse> getAll(Pageable pageable);
    TodoResponse getById(Long id);
    TodoResponse create(TodoRequest req);
    TodoResponse update(Long id, TodoRequest req);
    void delete(Long id);
}
