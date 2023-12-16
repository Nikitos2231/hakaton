package com.alibou.security.rest.dto.request;

import com.alibou.security.model.entity.enums.DirectoryType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectoryTypesRequest {

    @NotNull(message = "directory types list couldn't be null")
    private List<DirectoryType> types;
}
