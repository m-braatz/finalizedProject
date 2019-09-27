package com.example.northernTrust;

import com.example.northernTrust.model.Customer;
import com.example.northernTrust.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTestingNorthernTrust {

    @Autowired
    ServiceLayer serviceLayer;

    @Before
    public void setUp(){

        List<Customer> customers = serviceLayer.getAllCustomers();

        for (Customer customer: customers
			 ) {
                serviceLayer.deleteCustomer(customer.getId());
        }
    }

    @Test
    public void contextLoads(){

    }

    @Test
    public void addCustomerTest(){
        Customer customer = new Customer("Matt", "Braatz");
        Customer customer1 = new Customer();

        customer = serviceLayer.addCustomer(customer);

        customer1 = serviceLayer.getCustomer(customer.getId());

        assertEquals(customer, customer1);

    }

    @Test
    public void getCustomerByIdTest(){
        Customer customer = new Customer( "Matt", "Braatz");
        Customer customer2 = new Customer();

        customer = serviceLayer.addCustomer(customer);

        customer2 = serviceLayer.getCustomer(customer.getId());

        assertEquals(customer, customer2);

    }

    @Test
    public void updateCustomerTest(){

        Customer customer = new Customer("Matt", "Braatz");
        Customer customer1 = new Customer();

        customer = serviceLayer.addCustomer(customer);

        customer1 = serviceLayer.getCustomer(customer.getId());

        assertEquals(customer, customer1);

        customer.setFirstName("Matthew");
        customer1.setFirstName("Matthew");

        customer = serviceLayer.updateCustomer(customer);

        assertEquals(customer, customer1);
    }

    @Test
    public void deleteCustomerByIdTest(){
        Customer customer = new Customer("Matt", "Braatz");
        Customer customer1 = new Customer("John", "Doe");

        customer = serviceLayer.addCustomer(customer);

        customer1 = serviceLayer.getCustomer(customer.getId());

        assertEquals(customer, customer1);

        serviceLayer.deleteCustomer(customer.getId());

        customer = serviceLayer.getCustomer(customer.getId());

        assertNull(customer);

    }

    @Test
    public void getAllCustomersTests(){
        Customer customer1 = new Customer( "Matt", "A");
        Customer customer2 = new Customer( "Matt", "B");
        Customer customer3 = new Customer( "Matt", "C");

        serviceLayer.addCustomer(customer3);
        serviceLayer.addCustomer(customer2);
        serviceLayer.addCustomer(customer1);

        List<Customer> customersInOrder = new ArrayList<>();

        customersInOrder.add(customer1);
        customersInOrder.add(customer2);
        customersInOrder.add(customer3);

        List<Customer> listFromServiceLayer = serviceLayer.getAllCustomers();

        assertEquals(customersInOrder, listFromServiceLayer);


    }

}
