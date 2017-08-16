package com.o2.api;

import com.o2.dao.CustomerDao;
import com.o2.models.ConfConstant;
import com.o2.models.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MainApp {
    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            throw new Exception("Property file not specified");
        } else {
            BufferedReader bufferedReader;
            BufferedWriter bufferedWriter;

            try {
                ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
                CustomerDao customerJDBCTemplate = (CustomerDao) context.getBean("customerJDBCTemplate");
                ConfConstant constants = (ConfConstant) context.getBean("confConstant");
                String sCurrentLine;

                bufferedReader = new BufferedReader(new FileReader(constants.getInputFile()));
                bufferedWriter = new BufferedWriter(new FileWriter(constants.getOutputFile()));
                bufferedWriter.write("CUSTNUM,BFID,UID\n");
                while ((sCurrentLine = bufferedReader.readLine()) != null && !sCurrentLine.equals("")) {
                    customerJDBCTemplate.update(parseInt(sCurrentLine));
                    List<Customer> listOfCustomers = customerJDBCTemplate.getCustomerFromUserIdentity(parseInt(sCurrentLine));
                    for (Customer customer : listOfCustomers) {
                        bufferedWriter.write(customer.toString());
                        bufferedWriter.write("\n");
                        bufferedWriter.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
