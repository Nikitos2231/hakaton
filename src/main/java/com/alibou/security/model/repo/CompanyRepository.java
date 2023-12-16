package com.alibou.security.model.repo;

import com.alibou.security.model.entity.Company;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> findByName(String name);

    List<Company> findByNameContains(String namePart);

    @Transactional
    @Modifying
    @Query("UPDATE Company c SET c.name = :#{#company.name}, c.description = :#{#company.description} WHERE c.name = :name")
    void edit(@Param("name") String name, @Param("company") Company company);
}
