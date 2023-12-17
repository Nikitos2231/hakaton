package com.alibou.security.util.mapper;

import com.alibou.security.model.entity.Directory;
import com.alibou.security.rest.dto.DirectoryDto;
import com.alibou.security.rest.dto.request.AddDirectoryRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-17T17:39:49+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Eclipse Adoptium)"
)
@Component
public class DirectoryMapperImpl implements DirectoryMapper {

    @Override
    public DirectoryDto map(Directory directory) {
        if ( directory == null ) {
            return null;
        }

        DirectoryDto directoryDto = new DirectoryDto();

        directoryDto.setName( directory.getName() );
        directoryDto.setDescription( directory.getDescription() );

        return directoryDto;
    }

    @Override
    public Directory map(AddDirectoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Directory.DirectoryBuilder directory = Directory.builder();

        directory.name( request.getName() );
        directory.type( request.getType() );
        directory.description( request.getDescription() );

        return directory.build();
    }

    @Override
    public DirectoryDto mapToDto(AddDirectoryRequest request) {
        if ( request == null ) {
            return null;
        }

        DirectoryDto directoryDto = new DirectoryDto();

        directoryDto.setName( request.getName() );
        directoryDto.setDescription( request.getDescription() );

        return directoryDto;
    }
}
