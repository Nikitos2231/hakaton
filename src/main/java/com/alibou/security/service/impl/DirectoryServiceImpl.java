package com.alibou.security.service.impl;

import com.alibou.security.exception.DirectoryException;
import com.alibou.security.model.entity.Directory;
import com.alibou.security.model.entity.enums.DirectoryType;
import com.alibou.security.model.repo.DirectoryRepository;
import com.alibou.security.rest.dto.DirectoryDto;
import com.alibou.security.rest.dto.DirectoryValues;
import com.alibou.security.rest.dto.request.AddDirectoryRequest;
import com.alibou.security.rest.dto.request.DirectoriesNames;
import com.alibou.security.rest.dto.request.DirectoryTypesRequest;
import com.alibou.security.service.DirectoryService;
import com.alibou.security.util.mapper.DirectoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DirectoryServiceImpl implements DirectoryService {

    private final DirectoryRepository directoryRepository;
    private final DirectoryMapper directoryMapper;

    @Override
    public DirectoryDto getByName(String name) {
        return directoryRepository.findByName(name)
                .map(directoryMapper::map)
                .orElseThrow(() -> DirectoryException.notFoundByName(name));
    }

    @Override
    public DirectoryValues getDirectoryValuesByType(DirectoryTypesRequest request) {
        List<Directory> directories = directoryRepository.findByTypeIn(request.getTypes());
        return new DirectoryValues(parseDirectories(directories));
    }

    @Override
    public DirectoryDto save(AddDirectoryRequest request) {
        if (directoryRepository.findByName(request.getName()).isPresent()) {
            throw DirectoryException.alreadyExists(request.getName());
        }
        Directory directory = directoryRepository.save(directoryMapper.map(request));
        return directoryMapper.map(directory);
    }

    @Override
    public DirectoryDto edit(AddDirectoryRequest request, String name) {
        if (directoryRepository.findByName(name).isEmpty()) {
            throw DirectoryException.notFoundByName(name);
        }
        if (!name.equals(request.getName()) && directoryRepository.findByName(request.getName()).isPresent()) {
            throw DirectoryException.alreadyExists(request.getName());
        }
        directoryRepository.edit(name, directoryMapper.map(request));
        return directoryMapper.mapToDto(request);
    }

    @Override
    public List<DirectoryDto> getList(DirectoriesNames request) {
        return directoryRepository.findByNameIn(request.getDirectories())
                .stream()
                .map(directoryMapper::map)
                .toList();
    }

    private Map<DirectoryType, List<String>> parseDirectories(List<Directory> directories) {
        Map<DirectoryType, List<String>> result = new HashMap<>();
        directories.forEach(directory -> result
                .merge(directory.getType(), new ArrayList<>(List.of(directory.getName())), (prev, current) -> {
                    prev.addAll(current);
                    return prev;
                }));
        return result;
    }
}
