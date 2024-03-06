package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.ClientDTO;
import com.dolph.DolphBank.dto.EmployeeCreateDTO;
import com.dolph.DolphBank.dto.EmployeeDTO;
import com.dolph.DolphBank.entites.Account;
import com.dolph.DolphBank.entites.Person;

import java.util.List;

public interface EmployeeService {


    void createEmployee(EmployeeCreateDTO employeeCreateDTO, Person person);


    void updateEmployee(Long id, EmployeeCreateDTO employeeCreateDTO);

    List<EmployeeDTO> getAllEmployees();

    void fireEmployee(Long id);

    List<ClientDTO> getAllClients();

    void relocateClients(Long id);

    List<Account> getResponsibleAccounts();
}
