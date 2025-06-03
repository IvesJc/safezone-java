package com.adaptivedialogs.safezonejava.repositories;

import com.adaptivedialogs.safezonejava.entities.LogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<LogEntry, String> {
}