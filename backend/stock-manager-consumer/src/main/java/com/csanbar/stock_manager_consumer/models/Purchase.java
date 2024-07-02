package com.csanbar.stock_manager_consumer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Data
@Document(collection = "t_purchases")
public class Purchase {
    @Id
    private ObjectId id;
    @JsonProperty("pur_id")
    public long pur_id;
    @JsonProperty("pur_date")
    public Date pur_date;
    @JsonProperty("productList")
    public List<ProductPurchase> productList;
}
