package com.csanbar.stock_manager_consumer.events.suplier;

import com.csanbar.stock_manager_consumer.events.Event;
import com.csanbar.stock_manager_consumer.models.Supplier;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierCreatedEvent extends Event<Supplier> {
}
