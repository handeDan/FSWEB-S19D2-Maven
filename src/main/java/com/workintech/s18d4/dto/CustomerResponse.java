package com.workintech.s18d4.dto;

import java.util.List;

public record CustomerResponse(Long id, String firstName, String lastName, String email,double salary, String phoneNumber, List<AccountResponse> accounts) {}
