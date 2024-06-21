package com.csanbar.stock_manager_consumer.services.events;

import com.csanbar.stock_manager_consumer.events.Event;
import com.csanbar.stock_manager_consumer.events.product.ProductCreatedEvent;
import com.csanbar.stock_manager_consumer.events.product.ProductUpdatedEvent;
import com.csanbar.stock_manager_consumer.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductEventService {


    private final ObjectMapper objectMapper;

    private final ProductService productService;

    public ProductEventService(ObjectMapper objectMapper, ProductService productService) {
        this.objectMapper = objectMapper;
        this.productService = productService;
    }

    @KafkaListener(
            topics = "${topic.product.name:products}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1"
    )
    public void consumer(String event) {
        try {
            Event<?> productEvent = objectMapper.readValue(event, Event.class);
            if (productEvent instanceof ProductCreatedEvent productCreatedEvent) {
                productService.createProduct(productCreatedEvent.getData());
                log.info("Received Product ... with id={}, data={}",
                        productCreatedEvent.getId(),
                        productCreatedEvent.getData().toString());
            }else if(productEvent instanceof ProductUpdatedEvent productUpdatedEvent) {
                boolean updatedEvent = productService.updateProduct(productUpdatedEvent.getData());
                log.info("Updated state: {}",updatedEvent);
            }
            else {
                log.warn("Unknown event type: {}", productEvent.getType());
            }
        } catch (Exception e) {
            log.error("Error processing event: " + event, e);
        }
    }
}
