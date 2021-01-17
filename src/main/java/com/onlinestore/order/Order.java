package com.onlinestore.order;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // możliwa relacja
    private String username;

    @OneToMany
    private List<OrderLine> orderLines;

    @CreationTimestamp
    private LocalDateTime orderDate;

    private OrderStatus orderStatus;

    public Double calculateTotalPrice() {
        return orderLines.stream().mapToDouble(x -> x.getPrice() * x.getQuantity()).sum();
    }
}
