package net.javaguides.springboot.service.impl;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Department;
import net.javaguides.springboot.repository.DepartmentRepository;
import net.javaguides.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(this.getClass(),"GetDepartmentById: Department", "Id", id));

    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(int id, Department updatedDepartment) {

        Department department = departmentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(this.getClass(),"UpdateDepartment: Department","Id",id));

            department.setName(updatedDepartment.getName());
            department.setKey(updatedDepartment.getKey());
            department.setDateLastTimeModified(updatedDepartment.getDateLastTimeModified());
            departmentRepository.save(department);
        return department;
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException(this.getClass(),"DeleteDepartment: Department","Id",id));
        departmentRepository.deleteById(id);
    }
}
