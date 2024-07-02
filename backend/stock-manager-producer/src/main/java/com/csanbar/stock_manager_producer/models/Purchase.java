package com.csanbar.stock_manager_producer.models;

import java.sql.Date;
import java.util.List;

public class Purchase {
    public long pur_id;
    public Date pur_date;
    public List<ProductPurchase> productList;

    public Purchase(long pur_id, Date pur_date, List<ProductPurchase> productList) {
        this.pur_id = pur_id;
        this.pur_date = pur_date;
        this.productList = productList;
    }
}
