package com.csanbar.stock_manager_producer.models;

import java.sql.Date;

public class Product {
    public long pro_id;
    public String pro_name;
    public int pro_quantity;
    public Date pro_caducity;
    public Date pro_entry_date;

    public Product(long proId, String proName, int proQuantity, Date proCaducity, Date proEntryDate) {
        pro_id = proId;
        pro_name = proName;
        pro_quantity = proQuantity;
        pro_caducity = proCaducity;
        pro_entry_date = proEntryDate;
    }

    public Product(long proId) {
        pro_id = proId;
    }


}
