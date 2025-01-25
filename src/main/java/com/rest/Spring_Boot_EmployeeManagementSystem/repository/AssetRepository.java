package com.rest.Spring_Boot_EmployeeManagementSystem.repository;

import com.rest.Spring_Boot_EmployeeManagementSystem.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
}
