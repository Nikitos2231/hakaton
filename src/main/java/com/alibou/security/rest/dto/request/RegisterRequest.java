package com.alibou.security.rest.dto.request;

import com.alibou.security.model.entity.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "login couldn't be empty")
    private String login;
    @NotBlank(message = "password couldn't be empty")
    private String password;
    @NotNull(message = "role couldn't be empty")
    private Role role;
    private String company;
}
