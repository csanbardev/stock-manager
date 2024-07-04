package com.csanbar.stock_manager_consumer.services.events;

import com.csanbar.stock_manager_consumer.events.Event;
import com.csanbar.stock_manager_consumer.events.purchase.PurchaseCreatedEvent;
import com.csanbar.stock_manager_consumer.events.purchase.PurchaseUpdatedEvent;
import com.csanbar.stock_manager_consumer.services.PurchaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PurchaseEventService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final PurchaseService purchaseService;

    public PurchaseEventService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @KafkaListener(
            topics = "${topic.purchase.name:purchases}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1"
    )
    public void consumer(String event){
        try {
            Event<?> purchaseEvent = mapper.readValue(event, Event.class);

            if (purchaseEvent instanceof PurchaseCreatedEvent purchaseCreatedEvent) {
                purchaseService.createPurchase(purchaseCreatedEvent.getData());
                log.info("PurchaseCreatedEvent received .... id{}, data={}",
                        purchaseCreatedEvent.getId(),
                        purchaseCreatedEvent.getData().toString());
            }else if(purchaseEvent instanceof PurchaseUpdatedEvent purchaseUpdatedEvent) {
                purchaseService.updatePurchase(purchaseUpdatedEvent.getData());
                log.info("PurchaseUpdatedEvent received .... id{}, data={}",
                        purchaseUpdatedEvent.getId(),
                        purchaseUpdatedEvent.getData().toString());
            }
        } catch (Exception error) {
            log.error("Error processing event: " + event, error);
        }
    }
}
