package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String accountName;
  private Double moneyAmount;
  private String accountType;
  private String accountNumber;
  private double balance;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  @JsonIgnore // Döngüyü önler!
  private Customer customer;
}
