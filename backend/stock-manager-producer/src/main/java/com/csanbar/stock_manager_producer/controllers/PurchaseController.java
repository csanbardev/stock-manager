package com.csanbar.stock_manager_producer.controllers;

import com.csanbar.stock_manager_producer.models.Purchase;
import com.csanbar.stock_manager_producer.services.PurchaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchases")
    public long createPurchase(@RequestBody Purchase purchase) {
        return purchaseService.createPurchase(purchase);
    }
}
