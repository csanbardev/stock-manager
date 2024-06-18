package com.csanbar.stock_manager_producer.services;

import com.csanbar.stock_manager_producer.models.Product;
import com.csanbar.stock_manager_producer.repositories.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductEventsService productEventsService;

    public ProductService(ProductRepository repository, ProductEventsService productEventsService) {
        this.repository = repository;
        this.productEventsService = productEventsService;
    }


    public List<Product> getAll() {
        return repository.getAllProducts();
    }

    public long createProduct(Product product) {
        this.productEventsService.publish(product);
        return repository.createProduct(product);
    }

    public List<Product> getByCaducity(String caducity) {
        return repository.getAllByCaducity(caducity);
    }

    public List<Product> getByQuantity(String quantity) {
        return repository.getAllByQuantity(quantity);
    }

    public boolean updateProduct(Product product, String id) {
        return repository.updateProduct(product, id);
    }
}
