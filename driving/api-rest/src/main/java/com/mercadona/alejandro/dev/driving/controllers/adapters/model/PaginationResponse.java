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
 * PaginationResponse
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-14T05:55:16.740231+01:00[Europe/Madrid]", comments = "Generator version: 7.17.0")
public class PaginationResponse {

  private @Nullable Integer requestedPage;

  private @Nullable Integer requestedSize;

  private @Nullable Integer retrievedResults;

  private @Nullable Long totalResults;

  private @Nullable String nextPage;

  private @Nullable String previousPage;

  public PaginationResponse requestedPage(@Nullable Integer requestedPage) {
    this.requestedPage = requestedPage;
    return this;
  }

  /**
   * Get requestedPage
   * @return requestedPage
   */

  @Schema(name = "requestedPage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requestedPage")
  public @Nullable Integer getRequestedPage() {
    return requestedPage;
  }

  public void setRequestedPage(@Nullable Integer requestedPage) {
    this.requestedPage = requestedPage;
  }

  public PaginationResponse requestedSize(@Nullable Integer requestedSize) {
    this.requestedSize = requestedSize;
    return this;
  }

  /**
   * Get requestedSize
   * @return requestedSize
   */

  @Schema(name = "requestedSize", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requestedSize")
  public @Nullable Integer getRequestedSize() {
    return requestedSize;
  }

  public void setRequestedSize(@Nullable Integer requestedSize) {
    this.requestedSize = requestedSize;
  }

  public PaginationResponse retrievedResults(@Nullable Integer retrievedResults) {
    this.retrievedResults = retrievedResults;
    return this;
  }

  /**
   * Get retrievedResults
   * @return retrievedResults
   */

  @Schema(name = "retrievedResults", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("retrievedResults")
  public @Nullable Integer getRetrievedResults() {
    return retrievedResults;
  }

  public void setRetrievedResults(@Nullable Integer retrievedResults) {
    this.retrievedResults = retrievedResults;
  }

  public PaginationResponse totalResults(@Nullable Long totalResults) {
    this.totalResults = totalResults;
    return this;
  }

  /**
   * Get totalResults
   * @return totalResults
   */

  @Schema(name = "totalResults", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalResults")
  public @Nullable Long getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(@Nullable Long totalResults) {
    this.totalResults = totalResults;
  }

  public PaginationResponse nextPage(@Nullable String nextPage) {
    this.nextPage = nextPage;
    return this;
  }

  /**
   * Get nextPage
   * @return nextPage
   */

  @Schema(name = "nextPage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nextPage")
  public @Nullable String getNextPage() {
    return nextPage;
  }

  public void setNextPage(@Nullable String nextPage) {
    this.nextPage = nextPage;
  }

  public PaginationResponse previousPage(@Nullable String previousPage) {
    this.previousPage = previousPage;
    return this;
  }

  /**
   * Get previousPage
   * @return previousPage
   */

  @Schema(name = "previousPage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("previousPage")
  public @Nullable String getPreviousPage() {
    return previousPage;
  }

  public void setPreviousPage(@Nullable String previousPage) {
    this.previousPage = previousPage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaginationResponse paginationResponse = (PaginationResponse) o;
    return Objects.equals(this.requestedPage, paginationResponse.requestedPage) &&
        Objects.equals(this.requestedSize, paginationResponse.requestedSize) &&
        Objects.equals(this.retrievedResults, paginationResponse.retrievedResults) &&
        Objects.equals(this.totalResults, paginationResponse.totalResults) &&
        Objects.equals(this.nextPage, paginationResponse.nextPage) &&
        Objects.equals(this.previousPage, paginationResponse.previousPage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestedPage, requestedSize, retrievedResults, totalResults, nextPage, previousPage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaginationResponse {\n");
    sb.append("    requestedPage: ").append(toIndentedString(requestedPage)).append("\n");
    sb.append("    requestedSize: ").append(toIndentedString(requestedSize)).append("\n");
    sb.append("    retrievedResults: ").append(toIndentedString(retrievedResults)).append("\n");
    sb.append("    totalResults: ").append(toIndentedString(totalResults)).append("\n");
    sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
    sb.append("    previousPage: ").append(toIndentedString(previousPage)).append("\n");
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

