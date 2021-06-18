package com.payroll.service.employee;

import com.payroll.data.dto.EmployeeDto;
import com.payroll.data.model.Employee;
import com.payroll.web.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    Employee save(EmployeeDto employeeDto);

    Employee findById(Integer id);

    List<Employee> findAll();

    void deleteById(Integer id);

    Employee update(EmployeeDto employeeDto, Integer id) throws EmployeeNotFoundException;
}
