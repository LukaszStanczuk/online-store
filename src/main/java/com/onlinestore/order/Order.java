package com.onlinestore.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String totalCost;
    String orderAddress;
    String userAddress;
    LocalDateTime orderDate;
    OrderStatus orderStatus;
}
