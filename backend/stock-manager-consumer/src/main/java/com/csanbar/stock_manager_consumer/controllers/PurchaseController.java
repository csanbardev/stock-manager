package com.csanbar.stock_manager_consumer.controllers;

import com.csanbar.stock_manager_consumer.models.Product;
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


    @GetMapping("/purchases/{id}")
    public List<Product> getPurchaseState(@PathVariable Long id, @RequestParam String status) {
      return purchaseService.getProductsByState(id, status);
    }
}
