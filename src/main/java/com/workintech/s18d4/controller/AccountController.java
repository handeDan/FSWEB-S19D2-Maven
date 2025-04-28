package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/account")
public class AccountController {

  @Autowired private AccountService accountService;
  @Autowired private CustomerService customerService;

  @GetMapping
  public List<AccountResponse> getAllAccounts() {
    return accountService.findAll().stream().map(AccountResponse::new).toList();
  }

  @GetMapping("/{id}")
  public AccountResponse getAccountById(@PathVariable Long id) {
    Account account = accountService.find(id);
    return new AccountResponse(account);
  }

  @PostMapping("/{customerId}")
  public AccountResponse createAccount(@PathVariable Long customerId, @RequestBody Account account) {
    Customer customer = customerService.find(customerId); // Müşterinin varlığını kontrol et
    if (customer == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
    }
    Account createdAccount = accountService.save(account);
    return new AccountResponse(createdAccount);
  }

  @PutMapping("/{customerId}")
  public Account updateAccount(@PathVariable Long customerId, @RequestBody Account account) {
    // Müşteriyi bul
    Customer customer = customerService.find(customerId); // ✅ Eksik çağrı düzeltildi!

    if (customer == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
    }

    // Güncellenmiş hesap nesnesini oluştur
    account.setCustomer(customer);
    Account updatedAccount = accountService.save(account);
    return updatedAccount;
  }

  @DeleteMapping("/{id}")
  public Account deleteAccount(@PathVariable Long id) {
    Account deletedAccount = accountService.find(id);
    accountService.delete(id);
    return deletedAccount;
  }
}
