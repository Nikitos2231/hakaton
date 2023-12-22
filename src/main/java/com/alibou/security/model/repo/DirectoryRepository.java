package com.alibou.security.model.repo;

import com.alibou.security.model.entity.Directory;
import com.alibou.security.model.entity.enums.DirectoryType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DirectoryRepository extends JpaRepository<Directory, String> {
    Optional<Directory> findByName(String name);

    List<Directory> findByTypeIn(List<DirectoryType> types);

    @Transactional
    @Modifying
    @Query("UPDATE Directory d SET d.type = :#{#directory.type}, d.description = :#{#directory.description}, d.name = :#{#directory.name} WHERE d.name = :name")
    void edit(@Param("name") String name, @Param("directory") Directory directory);

    List<Directory> findByNameIn(List<String> names);
}
