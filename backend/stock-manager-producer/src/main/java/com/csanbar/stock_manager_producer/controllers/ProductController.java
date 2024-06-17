package com.csanbar.stock_manager_producer.controllers;

import com.csanbar.stock_manager_producer.models.Product;
import com.csanbar.stock_manager_producer.services.ProductService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/products/caducity/{caducity}")
    public List<Product> getProductsByCaducity(@PathVariable String caducity){return productService.getByCaducity(caducity);}

    @PostMapping("/products")
    public long addProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }


}
