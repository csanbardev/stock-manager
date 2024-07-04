package com.csanbar.stock_manager_consumer.events.purchase;

import com.csanbar.stock_manager_consumer.events.Event;
import com.csanbar.stock_manager_consumer.models.Purchase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseCreatedEvent extends Event<Purchase> {
}
