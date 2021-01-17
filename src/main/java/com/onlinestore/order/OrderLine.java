package com.onlinestore.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.onlinestore.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderLine {

  @Id
  private String username;

  @ManyToOne
  private Product product;

  private Integer quantity;

  private Double price;

  @ManyToOne
  private Order order;

}
