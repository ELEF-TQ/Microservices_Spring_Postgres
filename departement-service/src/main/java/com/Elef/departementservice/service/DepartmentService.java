package com.Elef.departementservice.service;

import com.Elef.departementservice.entity.Department;
import com.Elef.departementservice.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {


    @Autowired
    private DepartmentRepo departmentRepo;

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    // Get department by ID
    public Department getDepartmentById(Long id) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        return optionalDepartment.orElse(null);
    }

    // Create a new department
    public Department createDepartment(Department department) {
        return departmentRepo.save(department);
    }

    // Update an existing department
    public Department updateDepartment(Long id, Department updatedDepartment) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);

        if (optionalDepartment.isPresent()) {
            Department existingDepartment = optionalDepartment.get();
            // Update existingDepartment properties with the values from updatedDepartment
            existingDepartment.setDepartmentName (updatedDepartment.getDepartmentName());
            // Add more fields if needed

            return departmentRepo.save(existingDepartment);
        } else {
            // Department not found
            return null;
        }
    }

    // Delete a department by ID
    public void deleteDepartment(Long id) {
        departmentRepo.deleteById(id);
    }
}
