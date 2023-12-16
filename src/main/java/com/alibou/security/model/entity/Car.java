package com.alibou.security.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    @Id
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "tech_model")
    private String techModel;
    @Column(name = "engine_model")
    private String engineModel;
    @Column(name = "engine_serial_number")
    private String engineSerialNumber;
    @Column(name = "trans_model")
    private String transModel;
    @Column(name = "trans_serial_number")
    private String transSerialNumber;
    @Column(name = "drive_axle_model")
    private String driveAxleModel;
    @Column(name = "drive_axle_serial_number")
    private String driveAxleSerialNumber;
    @Column(name = "control_bridge_model")
    private String controlBridgeModel;
    @Column(name = "control_bridge_serial_number")
    private String controlBridgeSerialNumber;
    @Column(name = "factory_date_shipment")
    private LocalDate factoryDateShipment;
    private String client;
    private String consumer;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    private String equipment;
    private String company;
}
