package com.csanbar.stock_manager_consumer.events;



import com.csanbar.stock_manager_consumer.models.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCreatedEvent extends Event<Product> {

}
