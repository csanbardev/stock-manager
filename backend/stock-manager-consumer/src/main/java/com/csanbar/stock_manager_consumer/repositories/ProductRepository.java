package com.csanbar.stock_manager_consumer.repositories;

import com.csanbar.stock_manager_consumer.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
    Product findByProId(long id);

    Product findByProName(String proName);

    List<Product> findByProCaducityBefore(Date fechaLimite);

    List<Product> findByProQuantityIsLessThanEqual(int quantity);
}
