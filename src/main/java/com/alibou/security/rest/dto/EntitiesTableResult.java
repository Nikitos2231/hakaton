package com.alibou.security.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntitiesTableResult<T> {
    private List<T> entities;
    private Integer totalPages;
    private Integer totalElements;
}
