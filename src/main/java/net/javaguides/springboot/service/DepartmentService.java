package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Department;

import java.util.List;
import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department getDepartmentById(int id);

    Department createDepartment(Department department);

    Department updateDepartment(int id, Department updatedDepartment);

    void deleteDepartment(int id);
}
