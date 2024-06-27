package com.csanbar.stock_manager_consumer.services;

import com.csanbar.stock_manager_consumer.models.Product;
import com.csanbar.stock_manager_consumer.models.Supplier;
import com.csanbar.stock_manager_consumer.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductService productService;

    public SupplierService(SupplierRepository supplierRepository, ProductService productService) {
        this.supplierRepository = supplierRepository;
        this.productService = productService;
    }

    public boolean createSupplier(Supplier supplier) {
        try {
            List<Product> products = productService.getAllProductsById(supplier.productList);
            supplier.productList = products;
            supplierRepository.save(supplier);
            return true;
        } catch (Error e) {
            return false;
        }
    }
}
