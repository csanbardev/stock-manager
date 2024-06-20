package com.csanbar.stock_manager_consumer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Getter
@Setter
@Document(collection = "t_products")
public class Product {
    @Id
    private ObjectId id;
    @JsonProperty("pro_id")
    public long proId;
    @JsonProperty("pro_name")
    public String proName;
    @JsonProperty("pro_quantity")
    public int proQuantity;
    @JsonProperty("pro_caducity")
    public Date proCaducity;
    @JsonProperty("pro_entry_date")
    public Date proEntryDate;

    public Product(long proId, String proName, int proQuantity, Date proCaducity, Date proEntryDate) {
        this.id = new ObjectId();
        this.proId = proId;
        this.proName = proName;
        this.proQuantity = proQuantity;
        this.proCaducity = proCaducity;
        this.proEntryDate = proEntryDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", proId=" + proId +
                ", proName='" + proName + '\'' +
                ", proQuantity=" + proQuantity +
                ", proCaducity=" + proCaducity +
                ", proEntryDate=" + proEntryDate +
                '}';
    }
}
