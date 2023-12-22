package com.alibou.security.service;

import com.alibou.security.rest.dto.DirectoryDto;
import com.alibou.security.rest.dto.DirectoryValues;
import com.alibou.security.rest.dto.request.AddDirectoryRequest;
import com.alibou.security.rest.dto.request.DirectoriesNames;
import com.alibou.security.rest.dto.request.DirectoryTypesRequest;

import java.util.List;

public interface DirectoryService {

    DirectoryDto getByName(String name);

    DirectoryValues getDirectoryValuesByType(DirectoryTypesRequest request);

    DirectoryDto save(AddDirectoryRequest request);

    DirectoryDto edit(AddDirectoryRequest request, String name);

     List<DirectoryDto> getList(DirectoriesNames request);
}
