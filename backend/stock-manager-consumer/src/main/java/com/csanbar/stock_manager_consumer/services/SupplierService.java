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

    public boolean updateSupplier(Supplier supplier) {
        try {
            Supplier updated = supplierRepository.findBySupId(supplier.supId);

            if (updated != null) {
                updated.setSupName(supplier.supName);
                updated.setSupDetails(supplier.supDetails);

                if(!supplier.productList.isEmpty()){
                    updated.getProductList().removeIf(product -> product.proId == supplier.getProductList().get(0).proId);
                }

                supplierRepository.save(updated);
                return true;
            }
            return false;
        }catch (Error error){
            return false;
        }
    }


    public boolean deleteSupplier(Supplier supplier) {
        try {
            Supplier deleted = supplierRepository.findBySupId(supplier.supId);

            if (deleted != null) {
                supplierRepository.delete(deleted);
                return true;
            }
            return false;
        }catch (Error error){
            return false;
        }
    }
}
