package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.EmployeeCreateDTO;
import com.dolph.DolphBank.entites.Person;

public interface EmployeeService {
    void createEmployee(EmployeeCreateDTO employeeCreateDTO, Person person);
}
