package com.mercadona.alejandro.dev.application.exceptions;

import static com.mercadona.alejandro.dev.application.constants.ErrorCodes.PRODUCT_ERROR;

public class ProductNotFoundException extends BusinessException {

  public ProductNotFoundException(String message) {
    super(message);
    this.errorCode = PRODUCT_ERROR;
  }

  public ProductNotFoundException(String message, Object messageVariable) {
    super(message, messageVariable);
    this.errorCode = PRODUCT_ERROR;
  }
}
