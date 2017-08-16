package com.o2.dao;


import javax.sql.DataSource;
import java.util.List;

public interface CustomerDao {


    /**
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
    public void setDataSource(DataSource ds);

    /**
     * This is the method to be used to list down
     * a record from the user_identity table corresponding
     * to a passed custnum (default_accouont) and set the uid in customer.
     */
    public List getCustomerFromUserIdentity(int custNum);


    /**
     * This is the method to be used to update
     * a record into the Customer table.
     */
    public void update(int custNum);
}

