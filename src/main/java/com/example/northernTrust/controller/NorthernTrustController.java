package com.example.northernTrust.controller;

import com.example.northernTrust.model.Customer;
import com.example.northernTrust.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NorthernTrustController {

    @Autowired
    ServiceLayer serviceLayer;

    @RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id){
        return serviceLayer.getCustomer(id);
    }

    @RequestMapping(path = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer){
        return serviceLayer.addCustomer(customer);
    }

    @RequestMapping(path = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id){
        serviceLayer.deleteCustomer(id);
    }

    @RequestMapping(path = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer updateCustomer(@RequestBody Customer customer){
        return serviceLayer.updateCustomer(customer);
    }

    @RequestMapping(path = "/getAllCustomer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getSortedCustomers(){
        return serviceLayer.getAllCustomers();
    }
}
