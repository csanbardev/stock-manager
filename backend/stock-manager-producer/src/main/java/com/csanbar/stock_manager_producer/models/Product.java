package com.csanbar.stock_manager_producer.models;

import java.sql.Date;

public class Product {
    public final long pro_id;
    public final String pro_name;
    public final int pro_quantity;
    public final Date pro_caducity;
    public final Date pro_entry_date;

    public Product(long proId, String proName, int proQuantity, Date proCaducity, Date proEntryDate) {
        pro_id = proId;
        pro_name = proName;
        pro_quantity = proQuantity;
        pro_caducity = proCaducity;
        pro_entry_date = proEntryDate;
    }
}
