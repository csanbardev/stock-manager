package com.csanbar.stock_manager_consumer.repositories;

import com.csanbar.stock_manager_consumer.models.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuplierRepository extends MongoRepository<Supplier, Integer> {
}
