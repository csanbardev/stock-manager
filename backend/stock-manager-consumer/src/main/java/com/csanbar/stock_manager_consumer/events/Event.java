package com.csanbar.stock_manager_consumer.events;


import com.csanbar.stock_manager_consumer.events.product.ProductCreatedEvent;
import com.csanbar.stock_manager_consumer.events.product.ProductDeletedEvent;
import com.csanbar.stock_manager_consumer.events.product.ProductUpdatedEvent;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductCreatedEvent.class, name = "CREATED"),
        @JsonSubTypes.Type(value = ProductUpdatedEvent.class, name = "UPDATED"),
        @JsonSubTypes.Type(value = ProductDeletedEvent.class, name = "DELETED")
})
public abstract class Event <T> {
    private String id;
    private Date date;
    private EventType type;
    private T data;

}
