package com.o2.utilities;

import com.o2.dao.CustomerDao;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class CustomerJDBCTemplate implements CustomerDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void update(int custNum) {
        String SQL = "UPDATE CUSTOMER SET O2BFID=(O2BFID_SEQ.NEXTVAL) WHERE CUSTNUM= " + custNum;
        int updateRes = jdbcTemplateObject.update(SQL);
        System.out.println("Updated Record " + updateRes + "\n");
    }

    public void updateToNull(int custNum) {
        String SQL = "UPDATE CUSTOMER SET O2BFID=NULL WHERE CUSTNUM= " + custNum;
        int updateRes = jdbcTemplateObject.update(SQL);
        System.out.println("Updated Record " + updateRes + "\n");
    }

    public List getCustomerFromUserIdentity(int custNum) {
        String SQL = "SELECT a.IDENTITY_ID, b.CUSTNUM, b.O2BFID FROM USER_IDENTITY a , CUSTOMER b WHERE b.CUSTNUM=" + custNum + " AND a.DEFAULT_ACCOUNT=b.CUSTNUM;";
        return jdbcTemplateObject.query(SQL, new IdentityMapper());
    }
}