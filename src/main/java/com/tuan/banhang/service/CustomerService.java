package com.tuan.banhang.service;

import com.tuan.banhang.model.Customers;
import com.tuan.banhang.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public List<Customers> getAllCustomer() {
        return customerRepository.findAll();
    }
    public void deleteById(int theId) {
        customerRepository.deleteById(theId);
    }

}
