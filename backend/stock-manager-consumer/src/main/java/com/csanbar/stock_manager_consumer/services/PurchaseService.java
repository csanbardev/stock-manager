package com.csanbar.stock_manager_consumer.services;

import com.csanbar.stock_manager_consumer.models.Purchase;
import com.csanbar.stock_manager_consumer.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public boolean createPurchase(Purchase purchase) {
        try {
            purchaseRepository.save(purchase);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
