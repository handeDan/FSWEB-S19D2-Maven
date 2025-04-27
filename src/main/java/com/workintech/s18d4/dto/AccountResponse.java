package com.workintech.s18d4.dto;

import java.math.BigDecimal;

public record AccountResponse(Long id, String accountNumber, String accountType, Double balance, Long customerId) {
}