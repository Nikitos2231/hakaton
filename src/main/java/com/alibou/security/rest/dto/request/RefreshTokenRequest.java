package com.alibou.security.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(@NotBlank(message = "token couldn't be empty") String refreshToken) {
}
