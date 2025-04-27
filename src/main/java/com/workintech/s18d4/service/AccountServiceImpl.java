package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.AccountRepository;
import com.workintech.s18d4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public AccountServiceImpl(AccountRepository mockAccountRepository) {
        this.accountRepository = mockAccountRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    @Override
    public Account createAccount(Long customerId, Account account) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long customerId, Account updatedAccount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        Account existingAccount = accountRepository.findById(updatedAccount.getId())
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + updatedAccount.getId()));

        existingAccount.setAccountNumber(updatedAccount.getAccountNumber());
        existingAccount.setAccountType(updatedAccount.getAccountType());
        existingAccount.setBalance(updatedAccount.getBalance());
        existingAccount.setCustomer(customer);

        return accountRepository.save(existingAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
