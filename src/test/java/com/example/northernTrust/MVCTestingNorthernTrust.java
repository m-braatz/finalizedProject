package com.example.northernTrust;

import com.example.northernTrust.controller.NorthernTrustController;
import com.example.northernTrust.model.Customer;
import com.example.northernTrust.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NorthernTrustController.class)
public class MVCTestingNorthernTrust {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayerMock;

    @InjectMocks
    NorthernTrustController northernTrustController;

    private ObjectMapper mapper = new ObjectMapper();
    private String customerJson;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getCustomerMockMVCTest() throws Exception {

        Customer customer1 = new Customer(1, "Matt", "A");

       customerJson = mapper.writeValueAsString(customer1);

        when(serviceLayerMock.getCustomer(1)).thenReturn(customer1);

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(customerJson));

    }

    @Test
    public void getAllCustomersMockMVCTest() throws Exception {

        List<Customer> customers = new ArrayList<>();

        Customer customer1 = new Customer(1, "Matt", "A");
        Customer customer2 = new Customer(2, "Matt", "B");
        Customer customer3 = new Customer(3, "Matt", "C");

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        customerJson = mapper.writeValueAsString(customers);

        when(serviceLayerMock.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/getAllCustomer").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(customerJson));

    }

    @Test
    public void deleteCustomerMockMVCTest() throws Exception {

        Customer customer1 = new Customer(1, "Matt", "A");

        customerJson = mapper.writeValueAsString(customer1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/1").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void addCustomerMockMVCTest() throws Exception {

        Customer customer1 = new Customer(1, "Matt", "A");

        customerJson = mapper.writeValueAsString(customer1);

        when(serviceLayerMock.addCustomer(customer1)).thenReturn(customer1);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer").contentType(MediaType.APPLICATION_JSON)
                .content(customerJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(customerJson));

    }

    @Test
    public void updateCustomerMockMVCTest() throws Exception {

        Customer customer1 = new Customer(1, "Matt", "A");

        customerJson = mapper.writeValueAsString(customer1);

        mockMvc.perform(MockMvcRequestBuilders.put("/customer").contentType(MediaType.APPLICATION_JSON)
                .content(customerJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

}
