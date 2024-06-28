package com.csanbar.stock_manager_producer.models;

import java.util.List;

public class Supplier {
    public long sup_id;
    public String sup_name;
    public String sup_details;
    public List<SupplierProduct> supplierProductList;

    public Supplier(long sup_id) {
        this.sup_id = sup_id;
    }

    public Supplier(long sup_id, String sup_name, String sup_details, List<SupplierProduct> supplierProductList) {
        this.sup_id = sup_id;
        this.sup_name = sup_name;
        this.sup_details = sup_details;
        this.supplierProductList = supplierProductList;
    }
}
