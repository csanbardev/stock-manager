package com.csanbar.stock_manager_producer.models;


import java.sql.Date;

public class ProductPurchase {
    public long prp_id;
    public long prp_pur_id;
    public long prp_pro_id;
    public long prp_sup_id;
    public int prp_quantity;
    public Date prp_estimate_date;
    public String prp_status;

    public ProductPurchase(long prp_id, long prp_pur_id, long prp_pro_id, long prp_sup_id, int prp_quantity, Date prp_estimate_date, String prp_status) {
        this.prp_id = prp_id;
        this.prp_pur_id = prp_pur_id;
        this.prp_pro_id = prp_pro_id;
        this.prp_sup_id = prp_sup_id;
        this.prp_quantity = prp_quantity;
        this.prp_estimate_date = prp_estimate_date;
        this.prp_status = prp_status;
    }
}
