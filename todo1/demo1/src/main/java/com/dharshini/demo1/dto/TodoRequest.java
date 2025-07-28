package com.dharshini.demo1.dto;

import java.time.LocalDate;

public record TodoRequest(
        String title,
        String description,
        Boolean completed,
        LocalDate dueDate
) { }
