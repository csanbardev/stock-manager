package com.csanbar.stock_manager_consumer.controllers;

import com.csanbar.stock_manager_consumer.models.Product;
import com.csanbar.stock_manager_consumer.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/caducity/{caducity}")
    public List<Product> getProductsByCaducity(@PathVariable String caducity) {
        return productService.getByCaducity(Integer.parseInt(caducity));
    }

    @GetMapping("/products/quantity/{quantity}")
    public List<Product> getProductsByQuantity(@PathVariable String quantity) {
        return productService.getByQuantity(quantity);
    }

    @PostMapping("/products")
    public boolean createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PatchMapping("/products")
    public boolean updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

}
