package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.service.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  @Autowired private AccountService accountService;

  @GetMapping
  public List<AccountResponse> getAllAccounts() {
    return accountService.findAll().stream()
        .map(
            account ->
                new AccountResponse(
                    account.getId(),
                    account.getAccountNumber(),
                    account.getAccountType(),
                    account.getBalance(),
                    account.getCustomer().getId()))
        .toList();
  }

  @GetMapping("/{id}")
  public AccountResponse getAccountById(@PathVariable Long id) {
    Account account = accountService.find(id);
    return new AccountResponse(
        account.getId(),
        account.getAccountName(),
        account.getAccountType(),
        account.getBalance(),
        account.getCustomer().getId());
  }

  @PostMapping("/{customerId}")
  public AccountResponse createAccount(@PathVariable Long customerId, @RequestBody Account account) {
    Account createdAccount = accountService.save(account);
    return new AccountResponse(
        createdAccount.getId(),
        createdAccount.getAccountNumber(),
        createdAccount.getAccountType(),
        createdAccount.getBalance(),
        createdAccount.getCustomer().getId());
  }

  @PutMapping("/{customerId}")
  public AccountResponse updateAccount(@PathVariable Long customerId, @RequestBody Account account) {
    Account updatedAccount = accountService.update(customerId, account);
    return new AccountResponse(
        updatedAccount.getId(),
        updatedAccount.getAccountNumber(),
        updatedAccount.getAccountType(),
        updatedAccount.getBalance(),
        updatedAccount.getCustomer().getId());
  }

  @DeleteMapping("/{id}")
  public void deleteAccount(@PathVariable Long id) {
    accountService.delete(id);
  }
}
