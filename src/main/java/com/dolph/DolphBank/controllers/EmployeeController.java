package com.dolph.DolphBank.controllers;

import com.dolph.DolphBank.dto.AccountRequestDTO;
import com.dolph.DolphBank.dto.ClientDTO;
import com.dolph.DolphBank.entites.Account;
import com.dolph.DolphBank.repositories.PersonRepository;
import com.dolph.DolphBank.services.AccountService;
import com.dolph.DolphBank.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private final AccountService accountService;
    private final EmployeeService employeeService;
    private PersonRepository personRepository;

    @GetMapping("/account_requests")
    public ResponseEntity<List<AccountRequestDTO>> getAccountRequests() {
        return new ResponseEntity<>(accountService.getAllRequests(), HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return new ResponseEntity<>(employeeService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/responsible_accounts")
    public ResponseEntity<List<Account>> getResponsibleAccounts() {
        return new ResponseEntity<>(employeeService.getResponsibleAccounts(), HttpStatus.OK);
    }

    @PutMapping("/open_account/{id}")
    public ResponseEntity<String> respondToAccountOpenRequest(@PathVariable Long id){
        Long employeeId = getCurrentLoggedEmployeeId();
        return new ResponseEntity<>(accountService.openAccount(id, employeeId, new Date()), HttpStatus.OK);
    }

    private Long getCurrentLoggedEmployeeId() {
        return personRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getId();
    }


}
