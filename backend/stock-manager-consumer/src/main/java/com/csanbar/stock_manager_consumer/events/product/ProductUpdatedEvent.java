package com.csanbar.stock_manager_consumer.events.product;

import com.csanbar.stock_manager_consumer.events.Event;
import com.csanbar.stock_manager_consumer.models.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductUpdatedEvent extends Event<Product> {
}
