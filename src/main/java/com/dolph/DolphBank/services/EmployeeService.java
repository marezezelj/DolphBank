package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.EmployeeCreateDTO;
import com.dolph.DolphBank.dto.EmployeeDTO;
import com.dolph.DolphBank.entites.Employee;
import com.dolph.DolphBank.entites.Person;

import java.util.List;

public interface EmployeeService {


    void createEmployee(EmployeeCreateDTO employeeCreateDTO, Person person);


    void updateEmployee(Long id, EmployeeCreateDTO employeeCreateDTO);

    List<EmployeeDTO> getAllEmployees();

    void fireEmployee(Long id);
}
