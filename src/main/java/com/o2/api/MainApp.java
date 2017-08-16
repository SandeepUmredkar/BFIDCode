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
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class MainApp {
    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CustomerDao customerJDBCTemplate = (CustomerDao) context.getBean("customerJDBCTemplate");
        ConfConstant constants = (ConfConstant) context.getBean("confConstant");
        String sCurrentLine;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        System.out.println("Enter Your choice");
        System.out.println("1. For Adding BFID");
        System.out.println("2. For Reverting BFID change");
        Scanner userOperation = new Scanner(System.in);
        int operationNumber = Integer.parseInt(userOperation.next());
        switch (operationNumber) {
            case 1: {
                try {
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
                break;
            }
            case 2: {
                bufferedReader = new BufferedReader(new FileReader(constants.getInputFile()));
                while ((sCurrentLine = bufferedReader.readLine()) != null && !sCurrentLine.equals("")) {
                    customerJDBCTemplate.update(parseInt(sCurrentLine));

                }
                break;
            }
        }
    }
}
