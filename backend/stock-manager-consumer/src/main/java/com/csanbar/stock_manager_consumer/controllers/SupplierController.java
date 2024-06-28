package com.csanbar.stock_manager_consumer.controllers;

import com.csanbar.stock_manager_consumer.models.Supplier;
import com.csanbar.stock_manager_consumer.services.SupplierService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers() {
        return this.supplierService.getAllSuppliers();
    }

    @GetMapping("/suppliers/product/{id}")
    public List<Supplier> getAllSuppliersByProductId(@PathVariable String id) {
        return this.supplierService.getAllSuppliersByProductId(id);
    }
}
