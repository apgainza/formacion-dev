package com.mercadona.alejandro.dev.application.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class I18NKeys {

  @UtilityClass
  public static class Product{
    public static final String NOT_FOUND = "product.not_found";
    public static final String EXISTS = "product.exists";
  }

  @UtilityClass
  public static class Store{
    public static final String EXISTS = "store.exists";
    public static final String NOT_FOUND = "store.not_found";
  }

  @UtilityClass
  public static class CodeBar{
    public static final String INVALID = "code.bar.invalid";
  }

}
