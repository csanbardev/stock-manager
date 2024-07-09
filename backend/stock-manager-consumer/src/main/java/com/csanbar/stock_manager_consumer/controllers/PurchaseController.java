package com.csanbar.stock_manager_consumer.controllers;

import com.csanbar.stock_manager_consumer.models.PaginatedResponse;
import com.csanbar.stock_manager_consumer.models.Product;
import com.csanbar.stock_manager_consumer.models.Purchase;
import com.csanbar.stock_manager_consumer.services.PurchaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchases")
    public PaginatedResponse<Purchase> getAllPurchases(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return purchaseService.getAllPurchases(page, size);
    }

    @GetMapping("/purchases/state/{id}")
    public PaginatedResponse<Product> getPurchaseState(@PathVariable Long id, @RequestParam String status, @RequestParam(defaultValue = "0") int size, @RequestParam(defaultValue = "10") int page) {
        return purchaseService.getProductsByState(id, status, size, page);
    }
}
