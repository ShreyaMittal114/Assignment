package com.example.assignment.Exception;

public class ShelfNotFound extends  RuntimeException{
    public ShelfNotFound(String message) {
        super(message);
    }
}
