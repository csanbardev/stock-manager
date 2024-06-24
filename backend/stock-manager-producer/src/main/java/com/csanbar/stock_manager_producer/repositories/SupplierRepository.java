package com.csanbar.stock_manager_producer.repositories;

import com.csanbar.stock_manager_producer.models.Supplier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SupplierRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insert;
    private final String table = "t_suppliers";

    public SupplierRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("sup_id");
    }

    public long createSuplier(Supplier supplier) {
        try {
            return insert.executeAndReturnKey(
                    new MapSqlParameterSource()
                            .addValue("sup_name", supplier.sup_name)
                            .addValue("sup_details", supplier.sup_details)
            ).longValue();
        }catch (Error error){
            throw error;
        }
    }

}
