package com.csanbar.stock_manager_producer.services;

import com.csanbar.stock_manager_producer.models.Supplier;
import com.csanbar.stock_manager_producer.repositories.SupplierProductRepository;
import com.csanbar.stock_manager_producer.repositories.SupplierRepository;
import com.csanbar.stock_manager_producer.services.events.SupplierEventsService;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierProductRepository supplierProductRepository;
    private final SupplierEventsService supplierEventsService;


    public SupplierService(SupplierRepository supplierRepository, SupplierProductRepository supplierProductRepository, SupplierEventsService supplierEventsService) {
        this.supplierRepository = supplierRepository;
        this.supplierProductRepository = supplierProductRepository;
        this.supplierEventsService = supplierEventsService;
    }


    public long createSupplier(Supplier supplier) {
        try {
            long created = supplierRepository.createSuplier(supplier);

            supplierProductRepository.assignProducts(supplier.supplierProductList, created);
            supplier.sup_id = created;
            this.supplierEventsService.publish(supplier);
            return created;
        } catch (Error error) {
            throw error;
        }


    }
}
