package com.csanbar.stock_manager_consumer.services;

import com.csanbar.stock_manager_consumer.models.Product;
import com.csanbar.stock_manager_consumer.models.Supplier;
import com.csanbar.stock_manager_consumer.repositories.SuplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SuplierRepository suplierRepository;
    private final ProductService productService;

    public SupplierService(com.csanbar.stock_manager_consumer.repositories.SuplierRepository suplierRepository, ProductService productService) {
        this.suplierRepository = suplierRepository;
        this.productService = productService;
    }

    public boolean createSupplier(Supplier supplier) {
        try {
            List<Product> products = productService.getAllProductsById(supplier.productList);
            supplier.productList = products;
            suplierRepository.save(supplier);
            return true;
        } catch (Error e) {
            return false;
        }
    }
}
