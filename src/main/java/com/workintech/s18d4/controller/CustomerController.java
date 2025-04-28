package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired private CustomerService customerService;

  @GetMapping
  public List<Customer> getAllCustomers() {
    return customerService.findAll();
  }

  @GetMapping("/{id}")
  public CustomerResponse getCustomerById(@PathVariable Long id) {
    Customer customer = customerService.find(id);
    List<AccountResponse> accountResponses =
        customer.getAccounts().stream()
            .map(
                account ->
                    new AccountResponse(
                        account.getId(),
                        account.getAccountNumber(),
                        account.getAccountType(),
                        account.getBalance(),
                        customer.getId()))
            .toList();
    return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());
  }

  @PostMapping
  public Customer createCustomer(@RequestBody Customer customer) {
    return customerService.save(customer);
  }

  @PutMapping("/{id}")
  public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
    return customerService.update(id, customer);
  }

  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable Long id) {
    customerService.delete(id);
  }
}
