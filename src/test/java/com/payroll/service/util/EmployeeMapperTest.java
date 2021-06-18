package com.payroll.service.util;

import com.payroll.data.dto.EmployeeDto;
import com.payroll.data.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmployeeMapperTest {

    EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp() {
        employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    }

    @Test
    void givenEmployeeDtoSourceWhenMappedThenMapCorrectlyTest() {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Mike");
        employeeDto.setRole("Accountant");

        Employee employee = new Employee();

        employeeMapper.updateEmployeeFromDto(employeeDto, employee);

        assertThat(employee.getFirstName()).isEqualTo(employeeDto.getFirstName());
        assertThat(employee.getLastName()).isEqualTo(employeeDto.getLastName());
        assertThat(employee.getRole()).isEqualTo(employeeDto.getRole());
    }

    @Test
    void givenNullValuesWhenMappedThenMapNotNullValues() {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("John");

        Employee employee = new Employee();
        employee.setLastName("Bob");
        employee.setFirstName("Dan");
        employee.setRole("Mister");

        employeeMapper.updateEmployeeFromDto(employeeDto, employee);

        assertThat(employee.getFirstName()).isEqualTo("John");
        assertThat(employee.getLastName()).isEqualTo("Bob");
        assertThat(employee.getRole()).isEqualTo("Mister");

    }
}