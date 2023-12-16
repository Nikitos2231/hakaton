package com.alibou.security.util.mapper;

import com.alibou.security.model.entity.Directory;
import com.alibou.security.rest.dto.DirectoryDto;
import com.alibou.security.rest.dto.request.AddDirectoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DirectoryMapper {

    DirectoryDto map(Directory directory);

    Directory map(AddDirectoryRequest request);

    DirectoryDto mapToDto(AddDirectoryRequest request);
}
