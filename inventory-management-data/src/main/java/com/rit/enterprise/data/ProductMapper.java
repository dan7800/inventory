package com.rit.enterprise.data;

import com.rit.enterprise.core.Product;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ResultSetMapper<Product> {
    @Override
    public Product map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Product(r.getInt("product"),
                r.getString("name"),
                r.getInt("amount"),
                r.getDouble("base_price"),
                r.getDouble("base_cost"));
    }
}
