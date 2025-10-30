package com.mercadona.alejandro.dev.application.exceptions;

import com.mercadona.alejandro.dev.application.constants.ErrorCodes;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BusinessException extends RuntimeException {

  protected String errorCode = ErrorCodes.DEFAULT_ERROR;
  protected final List<Object> messageVariables = new ArrayList<>();
  private final List<String> details = new ArrayList<>();

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, List<Object> messageVariables) {
    super(message);
    this.messageVariables.addAll(messageVariables);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

  public BusinessException(String message, List<Object> messageVariables, Throwable cause) {
    super(message, cause);
    this.messageVariables.addAll(messageVariables);
  }

  public BusinessException(String message, Object messageVariable) {
    this(message, List.of(messageVariable));
  }
}
