package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.EmployeeCreateDTO;
import com.dolph.DolphBank.entites.Employee;
import com.dolph.DolphBank.entites.Person;
import com.dolph.DolphBank.repositories.ClientRepository;
import com.dolph.DolphBank.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private ClientRepository clientRepository;

    @Override
    public void createEmployee(EmployeeCreateDTO employeeCreateDTO, Person person) {

        clientRepository.removeClient(person.getId());
        employeeRepository.insertEmployee(person.getId(), employeeCreateDTO.getDepartment(), employeeCreateDTO.getJobRole());
    }
}
