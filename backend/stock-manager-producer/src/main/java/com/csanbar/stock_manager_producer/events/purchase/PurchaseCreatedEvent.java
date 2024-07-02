package com.csanbar.stock_manager_producer.events.purchase;

import com.csanbar.stock_manager_producer.events.Event;
import com.csanbar.stock_manager_producer.models.Purchase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseCreatedEvent extends Event<Purchase> {
}
