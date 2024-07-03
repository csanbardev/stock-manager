package com.csanbar.stock_manager_producer.repositories;

import com.csanbar.stock_manager_producer.models.ProductPurchase;
import com.csanbar.stock_manager_producer.models.SupplierProduct;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductPurchaseRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insert;
    private final String table = "t_products_purchases";

    public ProductPurchaseRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("prp_id");
    }


    public void assignPurchases(List<ProductPurchase> productList, long created) {
        try {
            String sql = "INSERT INTO " + table + " (prp_pur_id, prp_pro_id, prp_sup_id, prp_quantity, prp_estimate_date, prp_status) VALUES (:prp_pur_id, :prp_pro_id, :prp_sup_id, :prp_quantity, :prp_estimate_date, :prp_status)";
            List<MapSqlParameterSource> batchArgs = new ArrayList<>();

            for (ProductPurchase productPurchase : productList) {
                MapSqlParameterSource params = new MapSqlParameterSource();
                params.addValue("prp_pur_id", created);
                params.addValue("prp_pro_id", productPurchase.prp_pro_id);
                params.addValue("prp_sup_id", productPurchase.prp_sup_id);
                params.addValue("prp_quantity", productPurchase.prp_quantity);
                params.addValue("prp_estimate_date", productPurchase.prp_estimate_date);
                params.addValue("prp_status", productPurchase.prp_status);
                batchArgs.add(params);
            }

            MapSqlParameterSource[] batch = batchArgs.toArray(new MapSqlParameterSource[0]);
            namedParameterJdbcTemplate.batchUpdate(sql, batch);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
