package com.surenpi.autotest.phoenix.controller;

import com.surenpi.autotest.phoenix.entity.Customer;
import com.surenpi.autotest.phoenix.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SampleController {
    @Autowired
    private CustomerRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String home() {
        return "Hello World!";
    }

    @RequestMapping(value = "/crud", method = RequestMethod.GET)
    Iterable<Customer> crud(@RequestParam String name)
    {
        Iterable<Customer> list = repository.findByLastName(name);

        Customer customer = new Customer();
        customer.setLastName(name);

        repository.save(customer);

        list = repository.findAll();

        return list;
    }
}