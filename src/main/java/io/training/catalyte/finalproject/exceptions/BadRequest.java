package io.training.catalyte.finalproject.exceptions;

public class BadRequest extends RuntimeException {

  public BadRequest() {
  }

  public BadRequest(String message) {
    super(message);
  }
}
