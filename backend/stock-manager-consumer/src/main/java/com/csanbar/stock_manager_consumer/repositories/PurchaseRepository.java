package com.csanbar.stock_manager_consumer.repositories;

import com.csanbar.stock_manager_consumer.models.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepository extends MongoRepository<Purchase, Integer> {
}
