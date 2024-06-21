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
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/products/caducity/{caducity}")
    public List<Product> getProductsByCaducity(@PathVariable String caducity) {
        return productService.getByCaducity(caducity);
    }

    @GetMapping("/products/quantity/{quantity}")
    public List<Product> getProductsByQuantity(@PathVariable String quantity) {
        return productService.getByQuantity(quantity);
    }

    @PostMapping("/products")
    public long addProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PatchMapping("/products/{id}")
    public boolean updateProduct(@RequestBody Product product, @PathVariable String id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("products/{id}")
    public boolean deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }


}
