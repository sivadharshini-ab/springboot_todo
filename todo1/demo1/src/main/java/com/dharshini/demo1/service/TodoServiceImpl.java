package com.dharshini.demo1.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dharshini.demo1.dto.TodoRequest;
import com.dharshini.demo1.dto.TodoResponse;
import com.dharshini.demo1.entity.Todo;
import com.dharshini.demo1.exception.ResourceNotFoundException;
import com.dharshini.demo1.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repo;

    public TodoServiceImpl(TodoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Page<TodoResponse> getAll(Pageable pageable) {
        return repo.findAll(pageable).map(this::toResponse);
    }

    @Override
    public TodoResponse getById(Long id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Todo " + id + " not found"));
    }

    @Override
    public TodoResponse create(TodoRequest req) {
        Todo t = new Todo();
        apply(t, req);
        return toResponse(repo.save(t));
    }

    @Override
    public TodoResponse update(Long id, TodoRequest req) {
        Todo t = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo " + id + " not found"));
        apply(t, req);
        return toResponse(repo.save(t));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Todo " + id + " not found");
        }
        repo.deleteById(id);
    }

    private void apply(Todo t, TodoRequest req) {
        if (req.title() != null) t.setTitle(req.title());
        if (req.description() != null) t.setDescription(req.description());
        if (req.completed() != null) t.setCompleted(req.completed());
        if (req.dueDate() != null) t.setDueDate(req.dueDate());
    }

    private TodoResponse toResponse(Todo t) {
        return TodoResponse(
t.getId(),
t.getTitle(),
t.getDescription(),
t.getCompleted(),
t.getDueDate(),
t.getCreatedAt(),
t.getUpdatedAt()
);
    }

    private TodoResponse TodoResponse(Long id, String title, String description, boolean completed, LocalDate dueDate,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TodoResponse'");
    }
}
