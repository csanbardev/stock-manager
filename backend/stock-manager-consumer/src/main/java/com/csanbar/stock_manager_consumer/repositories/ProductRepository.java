package com.csanbar.stock_manager_consumer.repositories;

import com.csanbar.stock_manager_consumer.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
    Product findByProId(long id);

    Product findByProName(String proName);

    Page<Product> findByProCaducityBefore(Date limitDate, Pageable pageable);

    Page<Product> findByProQuantityIsLessThanEqual(int quantity, Pageable pageable);

    List<Product> findAllByProIdIn(List<Long> proIds);
}
