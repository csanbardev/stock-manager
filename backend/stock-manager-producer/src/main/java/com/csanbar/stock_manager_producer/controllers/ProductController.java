package com.csanbar.stock_manager_producer.controllers;

import com.csanbar.stock_manager_producer.models.Product;
import com.csanbar.stock_manager_producer.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAll();
    }

    @PostMapping("/products")
    public long addProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
}
