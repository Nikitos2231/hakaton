package com.alibou.security.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortCarDto {
    private String serialNumber;
    private String techModel;
    private String engineModel;
    private String engineSerialNumber;
    private String transModel;
    private String transSerialNumber;
    private String driveAxleModel;
    private String driveAxleSerialNumber;
    private String controlBridgeModel;
    private String controlBridgeSerialNumber;

}
