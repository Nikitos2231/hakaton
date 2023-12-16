package com.alibou.security.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Complaints {

    @Id
    private UUID id;
    @Column(name = "car_id")
    private String carId;
    @Column(name = "reject_date")
    private LocalDate rejectDate;
    @Column(name = "operating")
    private Integer operating;
    @Column(name = "reject_node")
    private String rejectNode;
    @Column(name = "reject_description")
    private String rejectDescription;
    @Column(name = "recovery_method")
    private String recoveryMethod;
    @Column(name = "spare_parts_used")
    private String sparePartsUsed;
    @Column(name = "recovery_date")
    private LocalDate recoveryDate;
    @Column(name = "equipment_downtime")
    private Integer equipmentDowntime;
    private String company;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "serial_number", insertable = false, updatable = false)
    private Car car;
}

