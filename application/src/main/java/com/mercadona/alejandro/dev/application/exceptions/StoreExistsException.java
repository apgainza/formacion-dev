package com.mercadona.alejandro.dev.application.exceptions;

import static com.mercadona.alejandro.dev.application.constants.ErrorCodes.STORE_ERROR;

public class StoreExistsException extends BusinessException {

  public StoreExistsException(String message) {
    super(message);
    this.errorCode = STORE_ERROR;
  }

  public StoreExistsException(String message, Object messageVariable) {
    super(message, messageVariable);
    this.errorCode = STORE_ERROR;
  }
}
