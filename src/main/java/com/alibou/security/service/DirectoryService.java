package com.alibou.security.service;

import com.alibou.security.rest.dto.DirectoryDto;
import com.alibou.security.rest.dto.DirectoryValues;
import com.alibou.security.rest.dto.request.AddDirectoryRequest;
import com.alibou.security.rest.dto.request.DirectoryTypesRequest;

public interface DirectoryService {

    DirectoryDto getByName(String name);

    DirectoryValues getDirectoryValuesByType(DirectoryTypesRequest request);

    DirectoryDto save(AddDirectoryRequest request);

    DirectoryDto edit(AddDirectoryRequest request, String name);
}
