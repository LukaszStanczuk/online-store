package com.onlinestore.order;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// odpowiednik koszyka na froncie
public class OrderPlacement {
  private List<OrderLine> orderLineList; // OrderLineDto
}
