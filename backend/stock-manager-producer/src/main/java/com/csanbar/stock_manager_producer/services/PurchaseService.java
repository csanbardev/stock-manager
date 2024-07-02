package com.csanbar.stock_manager_producer.services;

import com.csanbar.stock_manager_producer.controllers.PurchaseController;
import com.csanbar.stock_manager_producer.models.Purchase;
import com.csanbar.stock_manager_producer.repositories.ProductPurchaseRepository;
import com.csanbar.stock_manager_producer.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductPurchaseRepository productPurchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, ProductPurchaseRepository productPurchaseRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productPurchaseRepository = productPurchaseRepository;
    }

    public long createPurchase(Purchase purchase) {
        try {
            long created = purchaseRepository.createPurchase(purchase);

            productPurchaseRepository.assignPurchases(purchase.productList, created);
            purchase.pur_id = purchase.pur_id;

            return created;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
