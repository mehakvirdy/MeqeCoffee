package com.example.mycoffee.repository;

import com.example.mycoffee.model.CustomerOfTheMonth;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Arrays;
import java.util.Map;

@Repository
public class CustomerMonthRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CustomerOfTheMonth findCustomerOfTheMonth() {
        // Define the output parameters
        final SqlOutParameter customerIdParam = new SqlOutParameter("p_customer_id", Types.INTEGER);
        final SqlOutParameter customerNameParam = new SqlOutParameter("p_customer_name", Types.VARCHAR);
        final SqlOutParameter totalSpentParam = new SqlOutParameter("p_total_spent", Types.DECIMAL);

        // Execute stored procedure
        String sql = "{CALL GetCustomerOfTheMonth(?, ?, ?)}";

        Map<String, Object> result = jdbcTemplate.call(
                connection -> {
                    CallableStatement cs = connection.prepareCall(sql);
                    cs.registerOutParameter(1, Types.INTEGER);  // customer_id
                    cs.registerOutParameter(2, Types.VARCHAR);  // customer_name
                    cs.registerOutParameter(3, Types.DECIMAL);  // total_spent
                    return cs;
                },
                Arrays.asList(customerIdParam, customerNameParam, totalSpentParam)
        );

        // Retrieve the values
        Integer customerId = (Integer) result.get("p_customer_id");
        String customerName = (String) result.get("p_customer_name");
        BigDecimal totalSpent = (BigDecimal) result.get("p_total_spent");

        // Return the result as an object
        return new CustomerOfTheMonth(customerId, customerName, totalSpent.doubleValue());
    }
}