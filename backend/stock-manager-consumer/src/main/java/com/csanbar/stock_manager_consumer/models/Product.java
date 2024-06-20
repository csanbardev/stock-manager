package com.csanbar.stock_manager_consumer.models;

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
    public long proId;
    public String proName;
    public int proQuantity;
    public Date proCaducity;
    public Date proEntryDate;

    public Product(long proId, String proName, int proQuantity, Date proCaducity, Date proEntryDate) {
        this.id = new ObjectId();
        this.proId = proId;
        this.proName = proName;
        this.proQuantity = proQuantity;
        this.proCaducity = proCaducity;
        this.proEntryDate = proEntryDate;
    }
}
