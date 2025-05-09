package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.AccountRepository;
import com.workintech.s18d4.repository.CustomerRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired private AccountRepository accountRepository;

  @Autowired private CustomerRepository customerRepository;

  public AccountService(AccountRepository mockAccountRepository) {
    this.accountRepository = mockAccountRepository;
  }

  public List<Account> findAll() {
    return accountRepository.findAll();
  }

  public Account find(Long id) {
    return accountRepository.findById(id).orElse(null); // Exception yerine null döndür
  }

  public Account save(Account account) {
    return accountRepository.save(account);
  }

  public Account update(Long customerId, Account updatedAccount) {
    System.out.println("Customer ID: " + customerId);
    assert customerId != null : "customerId is null!";

    System.out.println(customerId);
    Long id = updatedAccount.getId();
    Customer customer =
        customerRepository
            .findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
    Account existingAccount = accountRepository.findById(id).orElse(null);
    if (existingAccount == null) {
      throw new NoSuchElementException("Account not found with id: " + id);
    }
    existingAccount.setAccountNumber(updatedAccount.getAccountNumber());
    existingAccount.setAccountType(updatedAccount.getAccountType());
    existingAccount.setBalance(updatedAccount.getBalance());
    existingAccount.setCustomer(customer);

    return accountRepository.save(existingAccount);
  }

  public Account delete(Long id) {
    Account account = this.find(id);
    if (account == null) {
      return null;
    }
    accountRepository.deleteById(id);
    return account;
  }
}
