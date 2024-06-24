package com.csanbar.stock_manager_producer.repositories;

import com.csanbar.stock_manager_producer.models.SupplierProduct;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SupplierProductRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insert;
    private final String table = "t_suppliers_products";

    public SupplierProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("spr_id");
    }

    public void assignProducts(List<SupplierProduct> supplierProductList, long created) {
        String sql = "INSERT INTO " + table + " (spr_pro_id, spr_sup_id) VALUES (:spr_pro_id, :spr_sup_id)";
        List<MapSqlParameterSource> batchArgs = new ArrayList<>();

        for (SupplierProduct supplierProduct : supplierProductList) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("spr_pro_id", supplierProduct.spr_pro_id);
            params.addValue("spr_sup_id", created);
            batchArgs.add(params);
        }

        MapSqlParameterSource[] batch = batchArgs.toArray(new MapSqlParameterSource[0]);
        namedParameterJdbcTemplate.batchUpdate(sql, batch);
    }
}
