package com.csanbar.stock_manager_consumer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@Document(collection = "t_purchases")
public class Purchase {
    @Id
    private ObjectId id;
    @JsonProperty("pur_id")
    public long purId;
    @JsonProperty("pur_date")
    public Date purDate;
    @JsonProperty("productList")
    public List<ProductPurchase> productList;
}
