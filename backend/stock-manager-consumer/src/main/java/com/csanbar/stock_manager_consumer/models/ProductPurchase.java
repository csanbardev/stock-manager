package com.csanbar.stock_manager_consumer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Data
public class ProductPurchase {
    @JsonProperty("prp_id")
    public long prpId;
    @JsonProperty("prp_pur_id")
    public long prpPurId;
    @JsonProperty("prp_pro_id")
    public long prpProId;
    @JsonProperty("prp_sup_id")
    public long prpSupId;
    @JsonProperty("prp_quantity")
    public int prpQuantity;
    @JsonProperty("prp_estimate_date")
    public Date prpEstimateDate;
    @JsonProperty("prp_status")
    public String prpStatus;
    public Product product;
    public Supplier supplier;

    @JsonSetter("prp_status")
    public void setPrpStatus(String prpStatus) {
        this.prpStatus = (prpStatus == null) ? "en proceso" : prpStatus;
    }

}
