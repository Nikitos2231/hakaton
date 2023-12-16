package com.alibou.security.rest.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    @Min(value = 0, message = "Page index must not be less than zero")
    private Integer pageNumber = 0;
    @Min(value = 1, message = "Page size must not be less than one")
    private Integer amountOfElements = 10;
}
