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

    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    public Department getDepartmentById(Long id) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        return optionalDepartment.orElse(null);
    }

    public Department createDepartment(Department department) {
        return departmentRepo.save(department);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);

        if (optionalDepartment.isPresent()) {
            Department existingDepartment = optionalDepartment.get();
            existingDepartment.setDepartmentName (updatedDepartment.getDepartmentName());
            return departmentRepo.save(existingDepartment);
        } else {
            return null;
        }
    }

    public void deleteDepartment(Long id) {
        departmentRepo.deleteById(id);
    }
}
