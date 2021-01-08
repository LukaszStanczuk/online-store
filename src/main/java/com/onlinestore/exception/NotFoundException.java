package com.onlinestore.exception;

public class NotFoundException extends RuntimeException {
   public NotFoundException(String message){
       super("NOT FOUND " + message);
   }
}
