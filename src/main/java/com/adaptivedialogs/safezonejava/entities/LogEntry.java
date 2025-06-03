package com.adaptivedialogs.safezonejava.entities;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "logs")
@Data
public class LogEntry {

    @Id
    private String id;
    private String message;
    private String level;
    private LocalDateTime timestamp;
    private String origem;

    // Getters e setters
}