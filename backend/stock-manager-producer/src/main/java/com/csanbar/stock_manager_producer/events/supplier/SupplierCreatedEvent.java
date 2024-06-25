package com.csanbar.stock_manager_producer.events.supplier;

import com.csanbar.stock_manager_producer.events.Event;
import com.csanbar.stock_manager_producer.models.Supplier;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierCreatedEvent extends Event<Supplier> {
}
