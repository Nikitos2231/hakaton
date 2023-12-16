package com.alibou.security.rest.dto;

import com.alibou.security.model.entity.enums.DirectoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectoryValues {
    Map<DirectoryType, List<String>> values;
}
