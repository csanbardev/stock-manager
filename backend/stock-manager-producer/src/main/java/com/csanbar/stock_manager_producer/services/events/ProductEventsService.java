package com.csanbar.stock_manager_producer.services.events;


import com.csanbar.stock_manager_producer.events.Event;
import com.csanbar.stock_manager_producer.events.EventType;
import com.csanbar.stock_manager_producer.events.product.ProductCreatedEvent;
import com.csanbar.stock_manager_producer.events.product.ProductUpdatedEvent;
import com.csanbar.stock_manager_producer.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ProductEventsService {


    private KafkaTemplate<String, Event<?>> producer;

    @Value("${topic.product.name:products}")
    private String topicProduct;

    public ProductEventsService(KafkaTemplate<String, Event<?>> producer) {
        this.producer = producer;
    }

    public void publish(Product product){
        ProductCreatedEvent created = new ProductCreatedEvent();

        created.setData(product);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(new Date());

        this.producer.send(topicProduct, created);
    }

    public void update(Product product){
        ProductUpdatedEvent updated = new ProductUpdatedEvent();

        updated.setData(product);
        updated.setId(UUID.randomUUID().toString());
        updated.setType(EventType.UPDATED);
        updated.setDate(new Date());

        this.producer.send(topicProduct, updated);
    }
}
