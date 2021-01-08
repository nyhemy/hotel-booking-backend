package io.training.catalyte.finalproject.exceptions;

public class Conflict extends RuntimeException {

  public Conflict() {
  }

  public Conflict(String message) {
    super(message);
  }
}
