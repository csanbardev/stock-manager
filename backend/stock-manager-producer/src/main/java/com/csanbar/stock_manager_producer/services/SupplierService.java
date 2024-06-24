package com.csanbar.stock_manager_producer.services;

import com.csanbar.stock_manager_producer.models.Supplier;
import com.csanbar.stock_manager_producer.repositories.SupplierProductRepository;
import com.csanbar.stock_manager_producer.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierProductRepository supplierProductRepository;


    public SupplierService(SupplierRepository supplierRepository, SupplierProductRepository supplierProductRepository) {
        this.supplierRepository = supplierRepository;
        this.supplierProductRepository = supplierProductRepository;
    }


    public long createSupplier(Supplier supplier) {
        try {
            long created = supplierRepository.createSuplier(supplier);

            supplierProductRepository.assignProducts(supplier.supplierProductList, created);

            return created;
        } catch (Error error) {
            throw error;
        }


    }
}
