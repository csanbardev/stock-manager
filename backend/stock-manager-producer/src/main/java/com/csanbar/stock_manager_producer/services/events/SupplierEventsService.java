package com.csanbar.stock_manager_producer.services.events;

import com.csanbar.stock_manager_producer.events.Event;
import com.csanbar.stock_manager_producer.events.EventType;
import com.csanbar.stock_manager_producer.events.product.ProductUpdatedEvent;
import com.csanbar.stock_manager_producer.events.supplier.SupplierCreatedEvent;
import com.csanbar.stock_manager_producer.events.supplier.SupplierDeletedEvent;
import com.csanbar.stock_manager_producer.events.supplier.SupplierUpdatedEvent;
import com.csanbar.stock_manager_producer.models.Product;
import com.csanbar.stock_manager_producer.models.Supplier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class SupplierEventsService {

    private KafkaTemplate<String, Event<?>> producer;


    @Value("${topic.supplier.name:suppliers}")
    private String topicProduct;

    public SupplierEventsService(KafkaTemplate<String, Event<?>> producer) {
        this.producer = producer;
    }

    public void publish(Supplier supplier){
        SupplierCreatedEvent created = new SupplierCreatedEvent();

        created.setData(supplier);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(new Date());

        this.producer.send(topicProduct, created);
    }

    public void update(Supplier supplier){
        SupplierUpdatedEvent updated = new SupplierUpdatedEvent();

        updated.setData(supplier);
        updated.setId(UUID.randomUUID().toString());
        updated.setType(EventType.UPDATED);
        updated.setDate(new Date());

        this.producer.send(topicProduct, updated);
    }

    public void delete(Supplier supplier){
        SupplierDeletedEvent deleted = new SupplierDeletedEvent();

        deleted.setData(supplier);
        deleted.setId(UUID.randomUUID().toString());
        deleted.setType(EventType.DELETED);
        deleted.setDate(new Date());

        this.producer.send(topicProduct, deleted);
    }
}
