package com.minisiasisten.app.vacancy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentVacancyNotEligibleException extends RuntimeException {
    public StudentVacancyNotEligibleException(String errorMessage) {
        super(errorMessage);
    }
}
