package com.dekankilic.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private String product;
    private double price;
    private String status;
}
