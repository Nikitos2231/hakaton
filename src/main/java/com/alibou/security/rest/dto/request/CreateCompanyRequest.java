package com.alibou.security.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCompanyRequest(@NotBlank(message = "Company name couldn't be empty") String name,
                                   String description) {
}
