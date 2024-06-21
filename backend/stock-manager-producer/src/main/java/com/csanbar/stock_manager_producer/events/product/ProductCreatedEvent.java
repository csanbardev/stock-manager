package com.csanbar.stock_manager_producer.events.product;


import com.csanbar.stock_manager_producer.events.Event;
import com.csanbar.stock_manager_producer.models.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCreatedEvent extends Event<Product> {

}
