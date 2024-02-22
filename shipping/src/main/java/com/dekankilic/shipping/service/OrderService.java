package com.dekankilic.shipping.service;

import com.dekankilic.shipping.dto.OrderPlacedEvent;
import com.dekankilic.shipping.model.Shipping;
import com.dekankilic.shipping.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ShippingRepository shippingRepository;
    private final KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "prod.orders.placed", groupId = "shipping-group")
    public void handleOrderPlacedEvent(OrderPlacedEvent event){
        Shipping shipping = new Shipping();
        shipping.setOrderId(event.getOrderId());
        shippingRepository.save(shipping);

        kafkaTemplate.send("prod.orders.shipped", String.valueOf(shipping.getOrderId()), String.valueOf(shipping.getOrderId()));
    }
}
