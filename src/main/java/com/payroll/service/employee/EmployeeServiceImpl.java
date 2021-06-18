package com.payroll.service.employee;

import com.payroll.data.dto.EmployeeDto;
import com.payroll.data.model.Employee;
import com.payroll.data.repository.EmployeeRepository;
import com.payroll.service.util.EmployeeMapper;
import com.payroll.web.exceptions.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    EmployeeMapper employeeMapper;

    EmployeeServiceImpl() {
        employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    }

    @Override
    public Employee save(EmployeeDto employeeDto) {
        Employee employee = new Employee();

        modelMapper.map(employeeDto, employee);

        log.info("Employee after mapping --> {}", employee);
        return employeeRepository.save(employee);

    }


    @Override
    public Employee findById(Integer id)  {

//        Optional<Employee> foundEmployee = employeeRepository.findById(id);
//        if (foundEmployee.isEmpty()) {
//            throw new Exception("The employee with id " + id + " does not exist");
//        }

//        Employee employee = foundEmployee.get();
//        return employee;

        //  Or
        return employeeRepository.findById(id).orElse(null);
    }


    @Override
    public List<Employee> findAll() {

        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }


    @Override
    public void deleteById(Integer id) {

        Optional<Employee> employee = employeeRepository.findById(id);
//        if (employee.isEmpty()) {
//            throw new Exception("Employee with the id " + id + " does not exists");
//        }
        employeeRepository.deleteById(id);
    }


    @Override
    public Employee update(EmployeeDto employeeDto, Integer id) throws EmployeeNotFoundException {

        Employee employee = employeeRepository.findById(id).orElse(null);

        if(employee == null) {
            throw new EmployeeNotFoundException("Employee not found");
        }

        employeeMapper.updateEmployeeFromDto(employeeDto, employee);
        log.info("Employee after mapping --> {}", employee);

        return employeeRepository.save(employee);
    }
// Or do it this way
//    if(findById(id) != null) {
//    employeeRepository.deleteById(id);
//}

}
