package com.mercadona.alejandro.dev.driving.controllers.adapters.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * StoreFilterRequest
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-14T05:55:16.740231+01:00[Europe/Madrid]", comments = "Generator version: 7.17.0")
public class StoreFilterRequest {

  private @Nullable String name;

  private @Nullable String code;

  private @Nullable String country;

  private @Nullable String product;

  private @Nullable Long category;

  public StoreFilterRequest name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */

  @Schema(name = "name", example = "La esprilla", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public StoreFilterRequest code(@Nullable String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   */

  @Schema(name = "code", example = "003117", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("code")
  public @Nullable String getCode() {
    return code;
  }

  public void setCode(@Nullable String code) {
    this.code = code;
  }

  public StoreFilterRequest country(@Nullable String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
   */

  @Schema(name = "country", example = "España", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("country")
  public @Nullable String getCountry() {
    return country;
  }

  public void setCountry(@Nullable String country) {
    this.country = country;
  }

  public StoreFilterRequest product(@Nullable String product) {
    this.product = product;
    return this;
  }

  /**
   * Get product
   * @return product
   */

  @Schema(name = "product", example = "003117", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("product")
  public @Nullable String getProduct() {
    return product;
  }

  public void setProduct(@Nullable String product) {
    this.product = product;
  }

  public StoreFilterRequest category(@Nullable Long category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
   */

  @Schema(name = "category", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("category")
  public @Nullable Long getCategory() {
    return category;
  }

  public void setCategory(@Nullable Long category) {
    this.category = category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StoreFilterRequest storeFilterRequest = (StoreFilterRequest) o;
    return Objects.equals(this.name, storeFilterRequest.name) &&
        Objects.equals(this.code, storeFilterRequest.code) &&
        Objects.equals(this.country, storeFilterRequest.country) &&
        Objects.equals(this.product, storeFilterRequest.product) &&
        Objects.equals(this.category, storeFilterRequest.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, code, country, product, category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoreFilterRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

