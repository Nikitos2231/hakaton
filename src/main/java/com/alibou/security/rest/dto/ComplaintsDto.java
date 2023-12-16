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
public class ComplaintsDto {

    private UUID id;
    @NotBlank(message = "car id must not be empty")
    private String carId;
    @NotNull(message = "reject date must not be empty")
    private LocalDate rejectDate;
    @NotNull(message = "operating must not be empty")
    private Integer operating;
    @NotBlank(message = "reject node must not be empty")
    private String rejectNode;
    @NotBlank(message = "reject description must not be empty")
    private String rejectDescription;
    @NotBlank(message = "recovery method must not be empty")
    private String recoveryMethod;
    @NotBlank(message = "spare parts used must not be empty")
    private String sparePartsUsed;
    @NotNull(message = "recovery date must not be empty")
    private LocalDate recoveryDate;
    @NotNull(message = "equipment downtime must not be empty")
    private Integer equipmentDowntime;
    @NotBlank(message = "company must not be empty")
    private String company;
}
