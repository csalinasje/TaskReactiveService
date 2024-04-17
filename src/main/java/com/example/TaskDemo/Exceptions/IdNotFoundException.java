package com.example.TaskDemo.Exceptions;


public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException (int id) {
        super("Id: "+id+" is not found");
    }
}
