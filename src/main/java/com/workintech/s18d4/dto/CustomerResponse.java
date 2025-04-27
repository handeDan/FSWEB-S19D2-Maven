package com.workintech.s18d4.dto;

import java.util.List;

public record CustomerResponse(Long id, String name, String email, String phoneNumber, List<AccountResponse> accounts) {
}
