package com.csanbar.stock_manager_consumer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "t_suppliers")
public class Supplier {
    @Id
    private ObjectId id;
    @JsonProperty("sup_id")
    public long supId;
    @JsonProperty("sup_name")
    public String supName;
    @JsonProperty("sup_details")
    public String supDetails;
    @JsonProperty("supplierProductList")
    public List<Product> productList;

    public Supplier(){}
    public Supplier(long supId, String supName, String supDetails, List<Product> productList) {
        this.id = new ObjectId();
        this.supId = supId;
        this.supName = supName;
        this.supDetails = supDetails;
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", supId=" + supId +
                ", supName='" + supName + '\'' +
                ", supDescription='" + supDetails + '\'' +
                ", productList=" + productList +
                '}';
    }
}
