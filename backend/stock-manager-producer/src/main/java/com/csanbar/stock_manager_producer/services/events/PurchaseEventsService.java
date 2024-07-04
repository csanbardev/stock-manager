package com.csanbar.stock_manager_producer.services.events;

import com.csanbar.stock_manager_producer.events.Event;
import com.csanbar.stock_manager_producer.events.EventType;
import com.csanbar.stock_manager_producer.events.purchase.PurchaseCreatedEvent;
import com.csanbar.stock_manager_producer.events.purchase.PurchaseUpdatedEvent;
import com.csanbar.stock_manager_producer.models.Purchase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PurchaseEventsService {
    private KafkaTemplate<String, Event<?>> producer;

    public PurchaseEventsService(KafkaTemplate<String, Event<?>> producer) {
        this.producer = producer;
    }

    @Value("${topic.purchase.name:purchases}")
    private String topicProduct;

    public void publish(Purchase purchase){
        PurchaseCreatedEvent created = new PurchaseCreatedEvent();

        created.setData(purchase);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.PURCHASE_CREATED);
        created.setDate(new Date());

        this.producer.send(topicProduct, created);
    }

    public void update(Purchase purchase){
        PurchaseUpdatedEvent updated = new PurchaseUpdatedEvent();

        updated.setData(purchase);
        updated.setId(UUID.randomUUID().toString());
        updated.setType(EventType.PURCHASE_UPDATED);
        updated.setDate(new Date());

        this.producer.send(topicProduct, updated);
    }
}
