package com.csanbar.stock_manager_producer.controllers;

import com.csanbar.stock_manager_producer.models.Suplier;
import com.csanbar.stock_manager_producer.services.SuplierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuplierController {

    private final SuplierService suplierService;

    public SuplierController(SuplierService suplierService) {
        this.suplierService = suplierService;
    }

    @PostMapping("/supliers")
    public long addProduct(@RequestBody Suplier suplier) {
        return suplierService.createSuplier(suplier);
    }
}
