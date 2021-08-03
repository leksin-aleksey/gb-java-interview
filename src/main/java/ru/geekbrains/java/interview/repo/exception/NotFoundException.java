package ru.geekbrains.java.interview.repo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Student")
public class NotFoundException extends Exception{
    private long id;

    public NotFoundException() {
    }

    public NotFoundException(long id) {
        this.id = id;
    }
}
