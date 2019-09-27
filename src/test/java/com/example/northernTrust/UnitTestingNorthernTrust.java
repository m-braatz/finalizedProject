package com.example.northernTrust;

import com.example.northernTrust.model.Customer;
import com.example.northernTrust.repository.CustomerRepository;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTestingNorthernTrust {


	ServiceLayer serviceLayerMock;
	CustomerRepository customerRepositoryMock;

	@Before
	public void setup(){
		setUpCustomerRepoMock();
		serviceLayerMock = new ServiceLayer(customerRepositoryMock);
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void listReturnCorrectLastNameAlphaOrderTest(){

		Customer customer1 = new Customer(1, "Matt", "A");
		Customer customer2 = new Customer(2, "Matt", "B");
		Customer customer3 = new Customer(3, "Matt", "C");

		List<Customer> customers = new ArrayList<>();

		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);

		List<Customer> customersFromMock = serviceLayerMock.getAllCustomers();

		assertEquals(customers, customersFromMock);



	}

	public void setUpCustomerRepoMock(){

		customerRepositoryMock = mock(CustomerRepository.class);

		Customer customer1 = new Customer(1, "Matt", "A");
		Customer customer2 = new Customer(2, "Matt", "B");
		Customer customer3 = new Customer(3, "Matt", "C");

		List<Customer> customers = new ArrayList<>();

		customers.add(customer3);
		customers.add(customer1);
		customers.add(customer2);

		when(customerRepositoryMock.findAll()).thenReturn(customers);

	}




}
