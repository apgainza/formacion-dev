package com.mercadona.alejandro.dev.driving.controllers.error;

import com.mercadona.alejandro.dev.application.exceptions.BusinessException;
import com.mercadona.alejandro.dev.application.exceptions.ProductNotFoundException;
import com.mercadona.alejandro.dev.application.exceptions.StoreNotFoundException;
import com.mercadona.alejandro.dev.application.exceptions.ValidationException;
import com.mercadona.alejandro.dev.application.services.MessagesService;
import com.mercadona.framework.cna.commons.rest.api.HttpErrorCode;
import com.mercadona.framework.cna.commons.rest.api.model.ErrorResource;
import com.mercadona.framework.cna.commons.rest.api.model.ErrorResourceResponse;
import com.mercadona.framework.cna.lib.error.handler.handlers.ErrorUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
@Order(0)
@Slf4j
public class ApiExceptionHandler {

  private final MessagesService messagesService;

  /*@ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<Object> handleExceptionValidation(Exception ex, WebRequest request) throws Exception {

    return this.handleMethodArgumentNotValid((MethodArgumentNotValidException)ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

  }*/

  @ExceptionHandler({ValidationException.class, ProductNotFoundException.class, StoreNotFoundException.class})
  public ResponseEntity<ErrorResourceResponse> handleBusinessException(BusinessException exception) {
    log.error("handleBusinessException :", exception);
    HttpStatus status = getStatusFromAnnotation(exception).orElse(HttpStatus.NOT_FOUND);

    return ResponseEntity.status(status).body(getErrorResponse(exception));
  }

  private Optional<HttpStatus> getStatusFromAnnotation(Exception e) {
    ResponseStatus responseStatus = e.getClass().getAnnotation(ResponseStatus.class);
    if (responseStatus == null) return Optional.empty();

    return Optional.of(responseStatus.value());
  }

  private ErrorResourceResponse getErrorResponse(BusinessException exception) {
    String codigoError =
      Optional.ofNullable(exception.getErrorCode())
        .orElse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    String mensaje = messagesService.getMessageSource(exception.getMessage(), exception.getMessageVariables());

    return ErrorUtility.getErrorResourceResponse(codigoError, mensaje, exception.getDetails());
  }

  /*protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    List errors = (List)exception.getBindingResult().getFieldErrors().stream().map((x) -> {
      String var10000 = x.getField();
      return var10000 + " " + x.getDefaultMessage();
    }).collect(Collectors.toList());
    body.put("errors", errors);
    ErrorResource errorRes = ErrorResource.builder().code(HttpErrorCode.VALIDATION_ERROR.getCode()).description(HttpErrorCode.VALIDATION_ERROR.getDescription()).details(errors).build();
    return new ResponseEntity<>(ErrorResourceResponse.builder().error(errorRes).build(), HttpErrorCode.VALIDATION_ERROR.getStatus());
  }*/
}
