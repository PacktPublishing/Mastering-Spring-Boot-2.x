package com.tomekl007.salary.infrastructure.exceptions;

public class UserNotFoundException extends Throwable {
  public UserNotFoundException(String msg) {
    super(msg);
  }
}
