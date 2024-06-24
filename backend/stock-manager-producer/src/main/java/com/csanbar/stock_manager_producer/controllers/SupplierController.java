package com.csanbar.stock_manager_producer.controllers;

import com.csanbar.stock_manager_producer.models.Supplier;
import com.csanbar.stock_manager_producer.services.SupplierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/suppliers")
    public long addProduct(@RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }
}
