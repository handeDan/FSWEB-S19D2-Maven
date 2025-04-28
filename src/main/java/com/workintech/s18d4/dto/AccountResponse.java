package com.workintech.s18d4.dto;

import com.workintech.s18d4.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {
  private Long id;
  private String accountName; // Eksik alanı ekleyin
  private String accountNumber;
  private String accountType;
  private Double balance;
  private Long customerId;

  public AccountResponse(Account account) {
    if (account != null) {
      this.id = account.getId();
      this.accountName = account.getAccountName();
      this.accountNumber = account.getAccountNumber();
      this.accountType = account.getAccountType();
      this.balance = account.getBalance();
      this.customerId = account.getCustomer().getId();
    }
  }
}
