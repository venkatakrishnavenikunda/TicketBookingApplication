package com.example.demo.show.exception;

public class ShowNotFoundException extends RuntimeException{
    public ShowNotFoundException(Long id){
        super("Show not found with id:"+id);
    }
}