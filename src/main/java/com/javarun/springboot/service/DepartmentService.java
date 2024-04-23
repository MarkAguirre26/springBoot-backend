package com.javarun.springboot.service;

import com.javarun.springboot.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department getDepartmentById(int id);

    Department createDepartment(Department department);

    Department updateDepartment(int id, Department updatedDepartment);

    void deleteDepartment(int id);
}
