package com.csanbar.stock_manager_producer.repositories;

import com.csanbar.stock_manager_producer.models.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insert;
    private final RecipeMapper mapper = new RecipeMapper();
    private final String table = "t_products";

    public ProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("pro_id");
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM "+table;
        return namedParameterJdbcTemplate.query(sql, mapper);
    }

    public long createProduct(Product product) {
        return insert.executeAndReturnKey(
                new MapSqlParameterSource()
                        .addValue("pro_name", product.pro_name)
                        .addValue("pro_quantity", product.pro_quantity)
                        .addValue("pro_caducity", product.pro_caducity)
                        .addValue("pro_entry_date", product.pro_entry_date)
        ).longValue();
    }

    private static class RecipeMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            long proId = rs.getLong("pro_id");
            String proName = rs.getString("pro_name");
            int proQuantity = rs.getInt("pro_quantity");
            Date proCaducity  = rs.getDate("pro_caducity");
            Date proEntryDate = rs.getDate("pro_entry_date");

            return new Product(proId, proName, proQuantity, proCaducity, proEntryDate);
        }
    }
}
