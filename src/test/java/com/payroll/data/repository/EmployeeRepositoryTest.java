package com.payroll.data.repository;

import com.payroll.data.dto.EmployeeDto;
import com.payroll.data.model.Employee;
import com.payroll.service.util.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts={"classpath:db/insert.sql"})
class EmployeeRepositoryTest {

    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeRepository employeeRepository;



    @BeforeEach
    void setUp() {
        employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    }

    @Test
    public void updateEmployeeRecordTest() {

        Employee employee = employeeRepository.findById(12).orElse(null);
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Bob");
        assertThat(employee.getLastName()).isEqualTo("Dan");

        log.info("Employee before save --> {}", employee);

        employee.setFirstName("John");

        employeeRepository.save(employee);
        assertThat(employee.getFirstName()).isEqualTo("John");
        assertThat(employee.getLastName()).isEqualTo("Dan");
        assertThat(employee.getRole()).isEqualTo("HR");

        log.info("Employee after save --> {}", employee);
    }

    @Test
    void givenEmployeeDtoSourceWhenMappedThenMapCorrectlyTest() {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("John");
        employeeDto.setLastName(null);
        employeeDto.setRole(null);

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