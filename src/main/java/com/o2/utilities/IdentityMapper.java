package com.o2.utilities;

import com.o2.models.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdentityMapper implements RowMapper {
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setO2bfid(rs.getLong("o2bfid"));
        customer.setCustnum(rs.getInt("custnum"));
        customer.setIdentity_id(rs.getString("identity_id"));
        return customer;
    }
}