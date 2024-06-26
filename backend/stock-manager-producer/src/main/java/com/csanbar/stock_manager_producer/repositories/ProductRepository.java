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
    private final ProductMapper mapper = new ProductMapper();
    private final String table = "t_products";

    public ProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("pro_id");
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM " + table;
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

    public List<Product> getAllByCaducity(String caducity) {
        String sql = "SELECT * FROM " + table + " WHERE abs(datediff(curdate(), pro_caducity)) <= :caducity";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("caducity", caducity);
        return namedParameterJdbcTemplate.query(sql, params, mapper);
    }

    public List<Product> getAllByQuantity(String quantity) {
        String sql = "SELECT * FROM " + table + " WHERE pro_quantity <= :quantity";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("quantity", quantity);
        return namedParameterJdbcTemplate.query(sql, params, mapper);
    }

    public boolean updateProduct(Product product, String id) {
        String sql = "UPDATE " + table + " SET pro_name = IFNULL(:name, pro_name), pro_quantity = IFNULL(:quantity, pro_quantity), pro_caducity = IFNULL(:caducity, pro_caducity), pro_entry_date = IFNULL(:entry_date,pro_entry_date) WHERE pro_id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("name", product.pro_name);
        params.addValue("quantity", product.pro_quantity);
        params.addValue("caducity", product.pro_caducity);
        params.addValue("entry_date", product.pro_entry_date);

        int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
        return rowsAffected > 0;

    }

    public boolean deleteProduct(String id) {
        String sql = "DELETE from " + table + " WHERE pro_id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
        return rowsAffected > 0;
    }

    private static class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            long proId = rs.getLong("pro_id");
            String proName = rs.getString("pro_name");
            int proQuantity = rs.getInt("pro_quantity");
            Date proCaducity = rs.getDate("pro_caducity");
            Date proEntryDate = rs.getDate("pro_entry_date");

            return new Product(proId, proName, proQuantity, proCaducity, proEntryDate);
        }
    }
}
