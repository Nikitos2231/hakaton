package com.alibou.security.model.entity;

import com.alibou.security.model.entity.enums.DirectoryType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Directory {
    @Id
    private String name;
    @Enumerated(EnumType.STRING)
    private DirectoryType type;
    private String description;

}
