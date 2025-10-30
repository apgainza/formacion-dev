package com.mercadona.alejandro.dev.application.exceptions;

import static com.mercadona.alejandro.dev.application.constants.ErrorCodes.PRODUCT_ERROR;
import static com.mercadona.alejandro.dev.application.constants.ErrorCodes.STORE_ERROR;

public class StoreNotFoundException extends BusinessException {

  public StoreNotFoundException(String message) {
    super(message);
    this.errorCode = STORE_ERROR;
  }

  public StoreNotFoundException(String message, Object messageVariable) {
    super(message, messageVariable);
    this.errorCode = STORE_ERROR;
  }
}
