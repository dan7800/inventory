package com.rit.enterprise.data;

import com.rit.enterprise.core.Log;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class LogMapper implements ResultSetMapper<Log> {

    @Override
    public Log map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Log(r.getInt("id"), r.getInt("transaction_id"), r.getString("description"), r.getTimestamp("timestamp").toLocalDateTime(), r.getInt("product_id"), r.getInt("amount_changed"));
    }
}
