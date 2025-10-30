package com.mercadona.alejandro.dev.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(staticName = "empty")
public class ProductFilter {

  private Long productId;
  private String name;
  private BigDecimal price;
  private Set<String> tags;

  public boolean hasProductId(){
    return productId != null;
  }

  public boolean hasName() {
    return StringUtils.isNotBlank(name);
  }

  public boolean hasPrice(){
    return price != null;
  }

  public boolean hasTags() {
    return tags != null;
  }

  public boolean hasAnyFilter(){
    return hasProductId() || hasName() || hasPrice() || hasTags();
  }

}
