package ru.mygradproject.util.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "You must input both dates")
public class DateBetweenException extends RuntimeException{
    public DateBetweenException(String message) {
        super(message);
    }
}
