package com.mercadona.alejandro.dev.application.exceptions;

import com.mercadona.framework.cna.commons.domain.MercadonaBusinessException;

import static com.mercadona.alejandro.dev.application.constants.ErrorCodes.CODE_BAR_ERROR;

public class ValidationException extends BusinessException {

  public ValidationException(String message) {
    super(message);
    this.errorCode = CODE_BAR_ERROR;
  }

  public ValidationException(String message, Object messageVariable) {
    super(message, messageVariable);
    this.errorCode = CODE_BAR_ERROR;
  }
}
