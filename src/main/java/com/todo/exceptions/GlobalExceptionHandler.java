package com.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TodoApiException.class)
    public ResponseEntity<ErrorMessage> handleTodoApiException(TodoApiException todoApiException, WebRequest webRequest){
      ErrorMessage errorMessage=new ErrorMessage(
              LocalDateTime.now(),
              todoApiException.getMessage(),
              webRequest.getDescription(false)
      );
      return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
