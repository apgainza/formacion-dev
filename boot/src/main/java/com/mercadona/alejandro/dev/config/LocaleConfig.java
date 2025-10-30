package com.mercadona.alejandro.dev.config;

import lombok.NonNull;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

@Component
public class LocaleConfig implements HandlerInterceptor {

  public static final String LOCALE_PARAM = "locale";
  public static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("es-es");

  @Override
  public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
    getLocaleDeRequest(request).ifPresentOrElse(this::setLocale, this::setDefaultLocale);
    return true;
  }

  private Optional<String> getLocaleDeRequest(HttpServletRequest request) {
    return Optional.ofNullable(request.getParameter(LOCALE_PARAM));
  }

  private void setDefaultLocale() {
    LocaleContextHolder.setLocale(DEFAULT_LOCALE);
  }

  private void setLocale(String localeStr) {
    Locale locale = mapLocale(localeStr);
    LocaleContextHolder.setLocale(locale);
  }

  private Locale mapLocale(String localeStr) {
    Locale locale = Locale.forLanguageTag(processLocale(localeStr));
    return StringUtils.hasLength(locale.getLanguage()) ? locale : DEFAULT_LOCALE;
  }

  private String processLocale(String localeStr) {
    return localeStr.replace("_", "-");
  }
}
