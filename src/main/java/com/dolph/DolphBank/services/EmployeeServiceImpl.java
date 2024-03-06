package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.ClientDTO;
import com.dolph.DolphBank.dto.EmployeeCreateDTO;
import com.dolph.DolphBank.dto.EmployeeDTO;
import com.dolph.DolphBank.entites.Account;
import com.dolph.DolphBank.entites.Client;
import com.dolph.DolphBank.entites.Employee;
import com.dolph.DolphBank.entites.Person;
import com.dolph.DolphBank.exceptions.NotFoundException;
import com.dolph.DolphBank.repositories.AccountRepository;
import com.dolph.DolphBank.repositories.ClientRepository;
import com.dolph.DolphBank.repositories.EmployeeRepository;
import com.dolph.DolphBank.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private ModelMapper mapper;
    private EmployeeRepository employeeRepository;
    private AccountRepository accountRepository;
    private ClientRepository clientRepository;
    private PersonRepository personRepository;

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

    @Override
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();

        return clients.stream().map(client -> mapper.map(client, ClientDTO.class)).toList();
    }

    @Override
    public void relocateClients(Long id) {
        Employee employee = employeeRepository.findEmployeeWithLeastResponsibleAccounts().stream().findFirst().orElseThrow(() -> new NotFoundException("No employees found."));

        List<Account> responsibleAccounts = getResponsibleAccounts(id);

        for(Account a : responsibleAccounts){
            a.setResponsibleEmployee(employee);
            accountRepository.save(a);
        }
    }

    @Override
    public List<Account> getResponsibleAccounts() {
        List<Account> accounts = accountRepository.findAllByResponsibleEmployee(findCurrentUser().getId()).orElseThrow(()-> new NotFoundException("No accounts found."));

        return accounts;
    }

    public List<Account> getResponsibleAccounts(Long id) {
        List<Account> accounts = accountRepository.findAllByResponsibleEmployee(findCurrentUser().getId()).orElseThrow(()-> new NotFoundException("No accounts found."));

        return accounts;
    }

    public Person findCurrentUser() {
        return personRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }
}
