package com.csanbar.stock_manager_consumer.services;

import com.csanbar.stock_manager_consumer.models.Product;
import com.csanbar.stock_manager_consumer.models.Purchase;
import com.csanbar.stock_manager_consumer.models.Supplier;
import com.csanbar.stock_manager_consumer.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final SupplierService supplierService;
    private final ProductService productService;

    public PurchaseService(PurchaseRepository purchaseRepository, SupplierService supplierService, ProductService productService) {
        this.purchaseRepository = purchaseRepository;
        this.supplierService = supplierService;
        this.productService = productService;
    }

    public boolean createPurchase(Purchase purchase) {
        try {
            purchase.setProductList(
                    purchase.getProductList().stream().map(productPurchase -> {
                        Optional<Product> productOpt = Optional.ofNullable(productService.getByProId(productPurchase.prpProId));
                        Optional<Supplier> supplierOpt = Optional.ofNullable(supplierService.getSupplierBySupId(productPurchase.prpSupId));

                        if (productOpt.isPresent() && supplierOpt.isPresent()) {
                            productPurchase.setProduct(productOpt.get());
                            productPurchase.setSupplier(supplierOpt.get());
                        } else {
                            throw new RuntimeException("Product or Supplier not found");
                        }
                        return productPurchase;
                    }).collect(Collectors.toList())
            );
            purchaseRepository.save(purchase);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
