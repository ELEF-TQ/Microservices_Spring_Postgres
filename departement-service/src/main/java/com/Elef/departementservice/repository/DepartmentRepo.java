package com.Elef.departementservice.repository;

import com.Elef.departementservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    // Your repository methods here
}
