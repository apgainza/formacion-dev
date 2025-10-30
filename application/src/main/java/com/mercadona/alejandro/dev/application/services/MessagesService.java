package com.mercadona.alejandro.dev.application.services;


import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessagesService {

  private final MessageSource messageSource;

  public String getMessageSource(String code, List<Object> args) {
    Locale locale = LocaleContextHolder.getLocale();
    try {
      return messageSource.getMessage(code, args.toArray(), locale);
    } catch (NoSuchMessageException exception) {
      return code;
    }
  }

  public String getMessageSource(String code, Object... args) {
    Locale locale = LocaleContextHolder.getLocale();
    try {
      return messageSource.getMessage(code, args, locale);
    } catch (NoSuchMessageException exception) {
      return code;
    }
  }
}
