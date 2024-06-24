package com.csanbar.stock_manager_producer.repositories;

import com.csanbar.stock_manager_producer.models.Product;
import com.csanbar.stock_manager_producer.models.Suplier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SuplierRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insert;
    private final String table = "t_supliers";

    public SuplierRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("sup_id");
    }

    public long createSuplier(Suplier suplier) {
        try {
            return insert.executeAndReturnKey(
                    new MapSqlParameterSource()
                            .addValue("sup_name", suplier.sup_name)
                            .addValue("sup_details", suplier.sup_details)
            ).longValue();
        }catch (Error error){
            throw error;
        }
    }

}
