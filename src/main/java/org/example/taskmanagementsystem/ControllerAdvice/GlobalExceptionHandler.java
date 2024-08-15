package org.example.taskmanagementsystem.ControllerAdvice;

import org.example.taskmanagementsystem.DTOs.ExceptionDTO;
import org.example.taskmanagementsystem.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity<ExceptionDTO> UserNotExistsExceptionHandler(UserNotExistsException e)
    {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(e.getMessage());
        exceptionDTO.setSolution("User not exists");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserWithSameUsernameExistsException.class)
    public ResponseEntity<ExceptionDTO> UserWithSameUsernameExistsExceptionHandler(UserWithSameUsernameExistsException e)
    {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(e.getMessage());
        exceptionDTO.setSolution("User with same Username already exists so please try someother username");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ExceptionDTO> InvalidPasswordException(InvalidPasswordException e)
    {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(e.getMessage());
        exceptionDTO.setSolution("Password that you have entered is wrong so please do check it once again");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ExceptionDTO> TaskNotFoundExceptionHandler(TaskNotFoundException e)
    {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(e.getMessage());
        exceptionDTO.setSolution("Please create the task before updating it");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException e)
    {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(e.getMessage());
        exceptionDTO.setSolution("So please do check once again");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ExceptionDTO> APIExceptionHandler(APIException e)
    {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(e.getMessage());
        exceptionDTO.setSolution("So please do check once again");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }




}
