package com.csanbar.stock_manager_producer.controllers;

import com.csanbar.stock_manager_producer.models.ProductPurchase;
import com.csanbar.stock_manager_producer.models.Purchase;
import com.csanbar.stock_manager_producer.services.PurchaseService;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/purchases/{id}/{product}")
    public boolean updateStatus(@PathVariable("id") long id, @PathVariable("product") long product, @RequestBody ProductPurchase productPurchase) {
        return purchaseService.updateStatus(id, product, productPurchase);
    }
}
