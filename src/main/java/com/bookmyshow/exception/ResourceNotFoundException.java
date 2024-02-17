package com.bookmyshow.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){

        super();
    }
    public ResourceNotFoundException(String msg){
        super(msg);
    }



}
