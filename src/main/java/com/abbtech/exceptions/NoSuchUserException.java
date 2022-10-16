package com.abbtech.exceptions;

public class NoSuchUserException extends RuntimeException{

  public NoSuchUserException() {
    super();
  }

  public NoSuchUserException(String message) {
    super(message);
  }

}
