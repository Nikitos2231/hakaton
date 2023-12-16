package com.alibou.security.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditCarRequest {
    private String serialNumber;
    @NotBlank(message = "tech number couldn't be empty")
    @Size(max = 100, message = "tech number could be max 100 characters")
    private String techModel;
    @NotBlank(message = "engine model couldn't be empty")
    @Size(max = 100, message = "engine model could be max 100 characters")
    private String engineModel;
    @NotBlank(message = "engine serial number couldn't be empty")
    @Size(max = 100, message = "engine serial number could be max 100 characters")
    private String engineSerialNumber;
    @NotBlank(message = "trans model couldn't be empty")
    @Size(max = 100, message = "trans model could be max 100 characters")
    private String transModel;
    @NotBlank(message = "trans serial number couldn't be empty")
    @Size(max = 100, message = "trans serial number could be max 100 characters")
    private String transSerialNumber;
    @NotBlank(message = "drive axle model couldn't be empty")
    @Size(max = 100, message = "drive axle model could be max 100 characters")
    private String driveAxleModel;
    @NotBlank(message = "drive axle serial number couldn't be empty")
    @Size(max = 100, message = "drive axle serial number could be max 100 characters")
    private String driveAxleSerialNumber;
    @NotBlank(message = "control bridge model couldn't be empty")
    @Size(max = 100, message = "control bridge model could be max 100 characters")
    private String controlBridgeModel;
    @NotBlank(message = "control bridge serial number couldn't be empty")
    @Size(max = 100, message = "control bridge serial number could be max 100 characters")
    private String controlBridgeSerialNumber;
    @NotNull(message = "factory date shipment couldn't be empty")
    private LocalDate factoryDateShipment;
    private String client;
    private String consumer;
    private String deliveryAddress;
    private String equipment;
    private String company;
}