package com.epam.employeemanagement.handler;


import com.epam.employeemanagement.dto.response.ErrorResponse;
import com.epam.employeemanagement.exception.EmployeeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse invalidArgumentResponse(MethodArgumentNotValidException e, WebRequest webRequest) {
        List<String> errorList = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(objectError -> errorList.add(objectError.getDefaultMessage()));
        return new ErrorResponse(new Date().toString(), errorList.toString(), webRequest.getDescription(false));
    }


    @ExceptionHandler(EmployeeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse customExceptionResponse(EmployeeException employeeException, WebRequest webRequest) {
        return new ErrorResponse(new Date().toString(), employeeException.getMessage(), webRequest.getDescription(false));
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse runTimeExceptionResponse(RuntimeException runtimeException, WebRequest webRequest) {
        log.info(runtimeException.toString());
        return new ErrorResponse(new Date().toString(), "Internal Server Issue, Check if all values are valid and try again", webRequest.getDescription(false));
    }




}
