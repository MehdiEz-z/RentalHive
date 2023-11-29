package com.youcode.rentalhive.handler.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
public class ErrorResponse {
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;

    private Object data;

    public ErrorResponse(int statusCode,Object data,String message) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.data = data;
        this.message = message;
    }
    public ErrorResponse(int statusCode,String message) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
    public int getStatusCode() {return statusCode;}
    public LocalDateTime getTimestamp() {return timestamp;}
    public String getMessage() {
        return message;
    }
    public Object getData() {
        return data;
    }
    public static ResponseEntity ok(Object data, String message){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(),data,message),HttpStatus.OK);
    }
    public static ResponseEntity notFound(String message){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(),message),HttpStatus.NOT_FOUND);
    }
    public static ResponseEntity created(Object data, String message){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.CREATED.value(),data,message),HttpStatus.CREATED);
    }
    public static ResponseEntity badRequest(String message){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),message),HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity unauthorized(String message){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),message),HttpStatus.UNAUTHORIZED);
    }
    public static ResponseEntity forbidden(String message){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.FORBIDDEN.value(),message),HttpStatus.FORBIDDEN);
    }
    public static ResponseEntity noContent(String message){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NO_CONTENT.value(),message),HttpStatus.NO_CONTENT);
    }
}
