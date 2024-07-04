package com.csanbar.stock_manager_consumer.services;

import com.csanbar.stock_manager_consumer.models.Product;
import com.csanbar.stock_manager_consumer.models.ProductPurchase;
import com.csanbar.stock_manager_consumer.models.Purchase;
import com.csanbar.stock_manager_consumer.models.Supplier;
import com.csanbar.stock_manager_consumer.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public void updatePurchase(Purchase purchase) {
        Purchase updated = purchaseRepository.findByPurId(purchase.purId);

        if (updated == null) {
            throw new RuntimeException("Purchase not exists");
        }

        if (!purchase.productList.isEmpty()) {
            updated.productList = updateProductList(updated.productList, purchase.productList);
        }

        purchaseRepository.save(updated);


    }

    public List<ProductPurchase> updateProductList(List<ProductPurchase> original, List<ProductPurchase> updated) {
        Map<Long, ProductPurchase> originalMap = new HashMap<>();
        for (ProductPurchase originalProduct : original) {
            originalMap.put(originalProduct.getPrpProId(), originalProduct);
        }

        for (ProductPurchase updatedProduct : updated) {
            ProductPurchase originalProduct = originalMap.get(updatedProduct.getPrpProId());
            if (originalProduct != null) {
                originalProduct.setPrpStatus(updatedProduct.getPrpStatus() != null ? updatedProduct.getPrpStatus() : originalProduct.getPrpStatus());
            }
        }

        return original;
    }

    public List<Product> getProductsByState(Long id, String status) {
        try {
            Purchase purchase = purchaseRepository.findByPurId(id);
            if (purchase == null) {
                throw new RuntimeException("Purchase not exists");
            }
            List<Product> filteredProductPurchases = purchase.productList.stream().filter(
                    productPurchase -> status.equals(productPurchase.prpStatus)
            ).map(ProductPurchase::getProduct).toList();

            return productService.getAllProductsById(filteredProductPurchases);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
