package com.dekankilic.inventory.controller;

import com.dekankilic.inventory.dto.InventoryStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InventoryController {
    private final Map<String, InventoryStatus> statuses = Map.of("1", new InventoryStatus(true), "2", new InventoryStatus(false));

    @GetMapping("/inventories")
    public InventoryStatus getInventory(@RequestParam String productId){
        return this.statuses.getOrDefault(productId, new InventoryStatus(false));
    }
}
