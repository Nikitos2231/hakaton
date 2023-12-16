package com.alibou.security.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tech_inspection")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechInspection {
    @Id
    private UUID id;
    @Column(name = "car_id")
    private String carId;
    private String type;
    @Column(name = "maintenance_date")
    private LocalDate maintenanceDate;
    private Integer operating;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "order_number_date")
    private LocalDate orderNumberDate;
    private String company;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "serial_number", insertable = false, updatable = false)
    private Car car;
}

