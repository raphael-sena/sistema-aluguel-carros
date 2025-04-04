package com.lab.backend.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DatabaseException extends DataIntegrityViolationException {
    public DatabaseException(String falhaDeIntegridadeReferencial) {
        super(falhaDeIntegridadeReferencial);
    }
}
