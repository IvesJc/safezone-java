package com.adaptivedialogs.safezonejava.usecases;


import com.adaptivedialogs.safezonejava.entities.LogEntry;
import com.adaptivedialogs.safezonejava.repositories.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    public void log(String message, String level, String origem) {
        LogEntry entry = new LogEntry();
        entry.setMessage(message);
        entry.setLevel(level);
        entry.setOrigem(origem);
        entry.setTimestamp(LocalDateTime.now());
        logRepository.save(entry);
    }
}