package com.example.northernTrust.service;

import com.example.northernTrust.model.Customer;
import com.example.northernTrust.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceLayer {

    @Autowired
    CustomerRepository customerRepository;

    public ServiceLayer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(int id){
       return customerRepository.getCustomerById(id);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll().stream().sorted(Comparator.comparing(Customer::getLastName)).collect(Collectors.toList());

    }

    public Customer updateCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteCustomer(int id){
        customerRepository.deleteById(id);
    }

}
