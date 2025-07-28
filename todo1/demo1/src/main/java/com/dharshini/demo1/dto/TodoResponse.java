package com.dharshini.demo1.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record TodoResponse(
        Long id,
        String title,
        String description,
        Boolean completed,
        LocalDate dueDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}