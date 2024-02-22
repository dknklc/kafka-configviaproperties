package com.dekankilic.order.service;

import com.dekankilic.order.client.InventoryClient;
import com.dekankilic.order.dto.InventoryStatus;
import com.dekankilic.order.dto.OrderPlacedEvent;
import com.dekankilic.order.dto.PlaceOrderRequest;
import com.dekankilic.order.model.Order;
import com.dekankilic.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate kafkaTemplate;
    private final InventoryClient inventoryClient;

    public void placeOrder(PlaceOrderRequest request){
        // check inventory service
        InventoryStatus status = inventoryClient.exists(request.getProduct());
        if(!status.isExists()){
            throw new EntityNotFoundException("Product does not exist");
        }

        // save into db
        Order newOrder = Order.builder()
                .product(request.getProduct())
                .price(request.getPrice())
                .status("PLACED")
                .build();
        Order savedOrder = orderRepository.save(newOrder);

        // public event
        this.kafkaTemplate.send("prod.orders.placed", String.valueOf(savedOrder.getId()), OrderPlacedEvent.builder()
                .orderId(savedOrder.getId().intValue())
                .product(request.getProduct())
                .price(request.getPrice())
                .build());
    }

    @KafkaListener(topics = "prod.orders.shipped", groupId = "order-group")
    public void handleOrderShippedEvent(String orderId){
        orderRepository.findById(Long.valueOf(orderId)).ifPresent(order -> {
            order.setStatus("SHIPPED");
            orderRepository.save(order);
        });
    }
}
