package com.miniproject.usermanagement.exceptions.advice;


import com.miniproject.usermanagement.exceptions.ForbidenActionException;
import com.miniproject.usermanagement.exceptions.InvalidCountUserException;
import com.miniproject.usermanagement.exceptions.InvalidFileIoException;
import com.miniproject.usermanagement.exceptions.InvalidUserNameOrEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
    //classe centralise et personnalise les exceptions

    @ExceptionHandler(InvalidFileIoException.class)
    public ResponseEntity<Object> handleInvalidFileIoException(
            InvalidFileIoException ex, WebRequest request) {
        //ex.printStackTrace();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("Type", "InvalidFileIoException");
        body.put("message", ex.getMessage());
        body.put("status", 400);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCountUserException.class)
    public ResponseEntity<Object> handleInvalidCountUserException(
            InvalidCountUserException ex, WebRequest request) {
        //ex.printStackTrace();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());

        body.put("message", ex.getMessage());
        body.put("status", 400);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidUserNameOrEmailException.class)
    public ResponseEntity<Object> handleInvalidUserNameOrEmailException(
            InvalidUserNameOrEmailException ex, WebRequest request) {
        //ex.printStackTrace();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());

        body.put("message", ex.getMessage());
        body.put("status", 401);
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ForbidenActionException.class)
    public ResponseEntity<Object> handleForbidenException(
            ForbidenActionException ex, WebRequest request) {
        //ex.printStackTrace();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());

        body.put("message", ex.getMessage());
        body.put("status", 403);
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
}