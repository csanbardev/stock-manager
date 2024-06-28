package com.csanbar.stock_manager_consumer.repositories;

import com.csanbar.stock_manager_consumer.models.Product;
import com.csanbar.stock_manager_consumer.models.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, Integer> {
    Supplier findBySupId(long supId);
    List<Supplier> findByProductListProId(long proId);
}
