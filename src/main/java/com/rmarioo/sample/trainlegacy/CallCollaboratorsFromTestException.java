package com.rmarioo.sample.trainlegacy;

public class CallCollaboratorsFromTestException extends RuntimeException
{
  public CallCollaboratorsFromTestException(Class aClass) {
    super("External service " + aClass.getSimpleName() + " should not be called directly from unit Test");
  }
}
