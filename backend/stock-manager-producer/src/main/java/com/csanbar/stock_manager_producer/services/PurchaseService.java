package com.csanbar.stock_manager_producer.services;

import com.csanbar.stock_manager_producer.models.ProductPurchase;
import com.csanbar.stock_manager_producer.models.Purchase;
import com.csanbar.stock_manager_producer.repositories.ProductPurchaseRepository;
import com.csanbar.stock_manager_producer.repositories.PurchaseRepository;
import com.csanbar.stock_manager_producer.services.events.PurchaseEventsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductPurchaseRepository productPurchaseRepository;
    private final PurchaseEventsService purchaseEventsService;

    public PurchaseService(PurchaseRepository purchaseRepository, ProductPurchaseRepository productPurchaseRepository, PurchaseEventsService purchaseEventsService) {
        this.purchaseRepository = purchaseRepository;
        this.productPurchaseRepository = productPurchaseRepository;
        this.purchaseEventsService = purchaseEventsService;
    }

    public long createPurchase(Purchase purchase) {
        try {
            long created = purchaseRepository.createPurchase(purchase);

            productPurchaseRepository.assignPurchases(purchase.productList, created);
            purchase.pur_id = created;

            purchaseEventsService.publish(purchase);

            return created;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateStatus(long id, long product, ProductPurchase productPurchase) {
        try {
            boolean updated = productPurchaseRepository.updateStatus(id, product, productPurchase);

            if(updated){
                Purchase updatedPurchase = new Purchase();
                updatedPurchase.pur_id = id;
                productPurchase.prp_pro_id = product;
                List<ProductPurchase> updatedProductList = new ArrayList<>();
                updatedProductList.add(productPurchase);
                updatedPurchase.productList = updatedProductList;

                purchaseEventsService.update(updatedPurchase);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
