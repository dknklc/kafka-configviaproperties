package com.dekankilic.order.controller;

import com.dekankilic.order.dto.PlaceOrderRequest;
import com.dekankilic.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody PlaceOrderRequest request){
        this.orderService.placeOrder(request);
    }
}
