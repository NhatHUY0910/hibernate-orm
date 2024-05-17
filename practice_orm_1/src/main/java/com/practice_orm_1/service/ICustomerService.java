package com.practice_orm_1.service;

import com.practice_orm_1.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    void addCustomer(Customer customer);
    void removeCustomer(Customer customer);
}
