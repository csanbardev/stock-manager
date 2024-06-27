package com.csanbar.stock_manager_consumer.services.events;

import com.csanbar.stock_manager_consumer.events.Event;
import com.csanbar.stock_manager_consumer.events.suplier.SupplierCreatedEvent;
import com.csanbar.stock_manager_consumer.events.suplier.SupplierDeletedEvent;
import com.csanbar.stock_manager_consumer.services.SupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SupplierEventService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final SupplierService supplierService;

    public SupplierEventService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @KafkaListener(
            topics = "${topic.supplier.name:suppliers}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1"
    )
    public void consumer(String event) {
        try {
            Event<?> suplierEvent = mapper.readValue(event, Event.class);

            if (suplierEvent instanceof SupplierCreatedEvent supplierCreatedEvent) {
                supplierService.createSupplier(supplierCreatedEvent.getData());
                log.info("SupplierCreatedEvent received .... id{}, data={}",
                        supplierCreatedEvent.getId(),
                        supplierCreatedEvent.getData().toString());
            }else if(suplierEvent instanceof SupplierDeletedEvent supplierDeletedEvent){
                supplierService.deleteSupplier(supplierDeletedEvent.getData());
                log.info("Supplier deleted....");
            }
        } catch (Exception error) {
            log.error("Error processing event: " + event, error);
        }
    }
}
