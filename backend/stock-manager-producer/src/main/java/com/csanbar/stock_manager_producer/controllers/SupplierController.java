package com.csanbar.stock_manager_producer.controllers;

import com.csanbar.stock_manager_producer.models.Supplier;
import com.csanbar.stock_manager_producer.services.SupplierService;
import org.springframework.web.bind.annotation.*;

@RestController
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/suppliers")
    public long addSupplier(@RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    @DeleteMapping("/suppliers/{id}")
    public boolean deleteSupplier(@PathVariable String id) {
        return supplierService.deleteSupplier(id);
    }
}
