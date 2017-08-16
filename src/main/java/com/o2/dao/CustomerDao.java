package com.o2.dao;


import javax.sql.DataSource;
import java.util.List;

public interface CustomerDao {
    void setDataSource(DataSource ds);

    List getCustomerFromUserIdentity(int custNum);

    void update(int custNum);

    void updateToNull(int custNum);
}

