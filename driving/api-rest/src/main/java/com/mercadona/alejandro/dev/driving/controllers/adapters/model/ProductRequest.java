package com.mercadona.alejandro.dev.driving.controllers.adapters.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mercadona.examen.esqueleto.model.ProductTypeRequestEnum;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ProductRequest
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-14T05:55:16.740231+01:00[Europe/Madrid]", comments = "Generator version: 7.17.0")
public class ProductRequest {

  private @Nullable String name;

  private @Nullable String code;

  private @Nullable Long category;

  private @Nullable ProductTypeRequestEnum state;

  public ProductRequest name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */

  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public ProductRequest code(@Nullable String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   */
  @Pattern(regexp = "^[0-9]{8}$")
  @Schema(name = "code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("code")
  public @Nullable String getCode() {
    return code;
  }

  public void setCode(@Nullable String code) {
    this.code = code;
  }

  public ProductRequest category(@Nullable Long category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
   */

  @Schema(name = "category", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("category")
  public @Nullable Long getCategory() {
    return category;
  }

  public void setCategory(@Nullable Long category) {
    this.category = category;
  }

  public ProductRequest state(@Nullable ProductTypeRequestEnum state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
   */
  @Valid
  @Schema(name = "state", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("state")
  public @Nullable ProductTypeRequestEnum getState() {
    return state;
  }

  public void setState(@Nullable ProductTypeRequestEnum state) {
    this.state = state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductRequest productRequest = (ProductRequest) o;
    return Objects.equals(this.name, productRequest.name) &&
        Objects.equals(this.code, productRequest.code) &&
        Objects.equals(this.category, productRequest.category) &&
        Objects.equals(this.state, productRequest.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, code, category, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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

