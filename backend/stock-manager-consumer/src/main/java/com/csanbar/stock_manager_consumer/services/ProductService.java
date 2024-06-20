package com.csanbar.stock_manager_consumer.services;

import com.csanbar.stock_manager_consumer.models.Product;
import com.csanbar.stock_manager_consumer.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public boolean createProduct(Product product) {
        try {

            productRepository.save(product);
            return true;
        } catch (Error error) {
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        return false;
    }

    public List<Product> getByCaducity(int caducity) {
        Date fechaLimite = new Date(System.currentTimeMillis() + (caducity * 24 * 60 * 60 * 1000));
        return productRepository.findByProCaducityBefore(fechaLimite);
    }

    public List<Product> getByQuantity(String quantity) {
        return productRepository.findByProQuantityIsLessThanEqual(Integer.parseInt(quantity));
    }
}
