package com.csanbar.stock_manager_producer.repositories;

import com.csanbar.stock_manager_producer.models.Purchase;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PurchaseRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insert;
    private final String table = "t_purchases";

    public PurchaseRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("pur_id");
    }

    public long createPurchase(Purchase purchase) {
        try {
            return insert.executeAndReturnKey(
                    new MapSqlParameterSource()
                            .addValue("pur_date", purchase.pur_date)
            ).longValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
