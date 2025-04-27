package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    Account createAccount(Long customerId, Account account);
    Account updateAccount(Long customerId, Account account);
    void deleteAccount(Long id);
}
