package com.dekankilic.order.client;

import com.dekankilic.order.dto.InventoryStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8082", name = "inventories")
public interface InventoryClient {
    @GetMapping("/inventories")
    InventoryStatus exists(@RequestParam String productId);
}
