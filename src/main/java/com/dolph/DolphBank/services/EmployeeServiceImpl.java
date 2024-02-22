package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.EmployeeCreateDTO;
import com.dolph.DolphBank.dto.EmployeeDTO;
import com.dolph.DolphBank.entites.Employee;
import com.dolph.DolphBank.entites.Person;
import com.dolph.DolphBank.exceptions.NotFoundException;
import com.dolph.DolphBank.repositories.ClientRepository;
import com.dolph.DolphBank.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private ModelMapper mapper;
    private EmployeeRepository employeeRepository;
    private ClientRepository clientRepository;

    @Override
    public void createEmployee(EmployeeCreateDTO employeeCreateDTO, Person person) {

        clientRepository.removeClient(person.getId());
        employeeRepository.insertEmployee(person.getId(), employeeCreateDTO.getDepartment(), employeeCreateDTO.getJobRole());
    }

    @Override
    public void updateEmployee(Long id, EmployeeCreateDTO employeeCreateDTO) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found."));

        if(employeeCreateDTO.getJobRole() != null && !employeeCreateDTO.getJobRole().isBlank()){
            employee.setRole(employeeCreateDTO.getJobRole());
        }

        if(employeeCreateDTO.getDepartment() != null && !employeeCreateDTO.getDepartment().isBlank()){
            employee.setDepartment(employeeCreateDTO.getDepartment());
        }

        employeeRepository.save(employee);

    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAllByActive(true);
        return employees.stream().map(employee -> mapper.map(employee, EmployeeDTO.class)).toList();
    }

    @Override
    public void fireEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new NotFoundException("Employee not found."));

        employee.setActive(false);

        employeeRepository.save(employee);
    }
}
