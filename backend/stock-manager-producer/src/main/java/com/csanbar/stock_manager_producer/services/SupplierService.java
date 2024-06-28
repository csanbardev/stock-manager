package com.csanbar.stock_manager_producer.services;

import com.csanbar.stock_manager_producer.models.Product;
import com.csanbar.stock_manager_producer.models.Supplier;
import com.csanbar.stock_manager_producer.models.SupplierProduct;
import com.csanbar.stock_manager_producer.repositories.SupplierProductRepository;
import com.csanbar.stock_manager_producer.repositories.SupplierRepository;
import com.csanbar.stock_manager_producer.services.events.SupplierEventsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public boolean deleteSupplier(String id) {
        try {

            boolean deleted = supplierRepository.deleteSupplier(id);

            if (deleted) {
                supplierEventsService.delete(new Supplier(Long.parseLong(id)));
                return true;
            }

            return false;
        } catch (Error error) {
            throw error;
        }
    }

    public boolean deleteProduct(String id, String product) {
        try {
            boolean deleted = supplierProductRepository.deleteProduct(id, product);
            if (deleted){
                Supplier supplier = new Supplier(Long.parseLong(id));
                SupplierProduct productDeleted = new SupplierProduct();
                productDeleted.spr_pro_id = Long.parseLong(product);
                List<SupplierProduct> productList = new ArrayList<>();
                productList.add(productDeleted);
                supplier.supplierProductList = productList;

                supplierEventsService.update(supplier);

                return true;
            }
        } catch (Error error) {
            throw error;
        }
        return false;
    }
}
