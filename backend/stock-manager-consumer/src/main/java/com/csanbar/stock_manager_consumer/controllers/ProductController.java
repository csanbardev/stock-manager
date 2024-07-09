package com.csanbar.stock_manager_consumer.controllers;

import com.csanbar.stock_manager_consumer.models.PaginatedResponse;
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
    public PaginatedResponse<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size
    ) {
        return productService.getAllProducts(page, size);
    }

    @GetMapping("/products/caducity")
    public PaginatedResponse<Product> getProductsByCaducity(@RequestParam int caducity,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return productService.getByCaducity(caducity, page, size);
    }

    @GetMapping("/products/quantity")
    public PaginatedResponse<Product> getProductsByQuantity(@RequestParam String quantity,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return productService.getByQuantity(quantity, page, size);
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
