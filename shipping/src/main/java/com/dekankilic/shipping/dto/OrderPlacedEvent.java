package com.dekankilic.shipping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedEvent {
    private int orderId;
    private String product;
    private double price;
}
