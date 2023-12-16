package com.alibou.security.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechInspectionDto {
    private UUID id;
    @NotBlank(message = "car id couldn't be null")
    private String carId;
    @NotBlank(message = "type must not be empty")
    private String type;
    @NotNull(message = "maintenance date must not be empty")
    private LocalDate maintenanceDate;
    @NotNull(message = "operating must not be empty")
    private Integer operating;
    @NotNull(message = "order number must not be empty")
    private String orderNumber;
    @NotNull(message = "order number date must not be empty")
    private LocalDate orderNumberDate;
    @NotBlank(message = "company couldn't be null")
    private String company;
}
