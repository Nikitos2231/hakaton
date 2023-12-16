package com.alibou.security.rest.dto.request;

import com.alibou.security.model.entity.enums.DirectoryType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDirectoryRequest {

    @Size(max = 100, message = "name could have 100 characters max")
    @NotNull(message = "name couldn't be null")
    private String name;
    @Size(max = 5000, message = "description could have 5000 characters max")
    private String description;
    @NotNull(message = "type couldn't be null")
    private DirectoryType type;
}
