package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.AccountRepository;
import com.workintech.s18d4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository mockCustomerRepository) {
        this.customerRepository = mockCustomerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer find(int id) {
        return customerRepository.findById(Long.parseLong(id+""))
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(int id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(Long.parseLong(id+""))
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(Long.parseLong(id+""));
    }
}
