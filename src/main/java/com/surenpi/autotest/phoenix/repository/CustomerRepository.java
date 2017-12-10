package com.surenpi.autotest.phoenix.repository;

import com.surenpi.autotest.phoenix.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long>
{
    List<Customer> findByLastName(String lastName);
}
