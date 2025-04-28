package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired private CustomerRepository customerRepository;

  public CustomerService(CustomerRepository mockCustomerRepository) {
    this.customerRepository = mockCustomerRepository;
  }

  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  public Customer find(Long id) {
    return customerRepository
        .findById(Long.parseLong(id + ""))
        .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
  }

  public Customer save(Customer customer) {
    return customerRepository.save(customer);
  }

  public Customer update(Long id, Customer updatedCustomer) {
    Customer existingCustomer =
        customerRepository
            .findById(Long.parseLong(id + ""))
            .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

    existingCustomer.setFirstName(updatedCustomer.getFirstName());
    existingCustomer.setEmail(updatedCustomer.getEmail());
    existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());

    return customerRepository.save(existingCustomer);
  }

  public Customer delete(Long id) {
    Optional<Customer> find = customerRepository.findById(id);
    customerRepository.deleteById(id);
    return find.orElse(null);
  }
}
