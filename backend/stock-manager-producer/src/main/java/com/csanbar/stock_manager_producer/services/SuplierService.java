package com.csanbar.stock_manager_producer.services;

import com.csanbar.stock_manager_producer.models.Suplier;
import com.csanbar.stock_manager_producer.repositories.SuplierProductRepository;
import com.csanbar.stock_manager_producer.repositories.SuplierRepository;
import org.springframework.stereotype.Service;

@Service
public class SuplierService {

    private final SuplierRepository suplierRepository;
    private final SuplierProductRepository suplierProductRepository;


    public SuplierService(SuplierRepository suplierRepository, SuplierProductRepository suplierProductRepository) {
        this.suplierRepository = suplierRepository;
        this.suplierProductRepository = suplierProductRepository;
    }


    public long createSuplier(Suplier suplier) {
        try {
            long created = suplierRepository.createSuplier(suplier);

            suplierProductRepository.assignProducts(suplier.suplierProductList, created);

            return created;
        } catch (Error error) {
            throw error;
        }


    }
}
