package com.csanbar.stock_manager_consumer.services;

import com.csanbar.stock_manager_consumer.models.PaginatedResponse;
import com.csanbar.stock_manager_consumer.models.Product;
import com.csanbar.stock_manager_consumer.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
       try {
           Product updated = productRepository.findByProId(product.proId);

           if (updated != null) {
               updated.setProName(product.proName);
               updated.setProCaducity(product.proCaducity);
               updated.setProQuantity(product.proQuantity);
               updated.setProEntryDate(product.proEntryDate);

               productRepository.save(updated);
               return true;
           }
           return false;
       }catch (Error error){
           return false;
       }
    }

    public boolean deleteProduct(Product product) {
        try {
            Product deleted = productRepository.findByProId(product.proId);

            if (deleted != null) {
                productRepository.delete(deleted);
                return true;
            }
            return false;
        }catch (Error error){
            return false;
        }
    }

    public PaginatedResponse<Product> getByCaducity(int caducity, int page, int size) {
        Date limitDate = new Date(System.currentTimeMillis() + (caducity * 24 * 60 * 60 * 1000));
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByProCaducityBefore(limitDate, pageable);

        return new PaginatedResponse<>(
                productPage.getContent(),
                productPage.getTotalPages(),
                productPage.getTotalElements(),
                productPage.getNumber()
        );
    }

    public PaginatedResponse<Product> getByQuantity(String quantity, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByProQuantityIsLessThanEqual(Integer.parseInt(quantity), pageable);

        return  new PaginatedResponse<>(
                productPage.getContent(),
                productPage.getTotalPages(),
                productPage.getTotalElements(),
                productPage.getNumber()
        );
    }

    public List<Product> getAllProductsById(List<Product> productList) {
        List<Long> proIds = productList.stream()
                .map(Product::getProId)
                .collect(Collectors.toList());

        List<Product> products = productRepository.findAllByProIdIn(proIds);
        return products;
    }

    public Product getByProId(long proId) {
        return productRepository.findByProId(proId);
    }
}

