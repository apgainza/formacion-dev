package com.mercadona.alejandro.dev.driving.controllers.adapters.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * StorePageResponse
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-14T05:55:16.740231+01:00[Europe/Madrid]", comments = "Generator version: 7.17.0")
public class StorePageResponse {

  @lombok.Builder.Default
  @Valid
  private List<@Valid StoreResponse> data = new ArrayList<>();

  private @Nullable PaginationResponse pagination;

  public StorePageResponse data(List<@Valid StoreResponse> data) {
    this.data = data;
    return this;
  }

  public StorePageResponse addDataItem(StoreResponse dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Get data
   * @return data
   */
  @Valid
  @Schema(name = "data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("data")
  public List<@Valid StoreResponse> getData() {
    return data;
  }

  public void setData(List<@Valid StoreResponse> data) {
    this.data = data;
  }

  public StorePageResponse pagination(@Nullable PaginationResponse pagination) {
    this.pagination = pagination;
    return this;
  }

  /**
   * Get pagination
   * @return pagination
   */
  @Valid
  @Schema(name = "pagination", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pagination")
  public @Nullable PaginationResponse getPagination() {
    return pagination;
  }

  public void setPagination(@Nullable PaginationResponse pagination) {
    this.pagination = pagination;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StorePageResponse storePageResponse = (StorePageResponse) o;
    return Objects.equals(this.data, storePageResponse.data) &&
        Objects.equals(this.pagination, storePageResponse.pagination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, pagination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StorePageResponse {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    pagination: ").append(toIndentedString(pagination)).append("\n");
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

