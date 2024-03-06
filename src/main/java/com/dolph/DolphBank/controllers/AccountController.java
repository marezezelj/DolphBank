package com.dolph.DolphBank.controllers;

import com.dolph.DolphBank.dto.AccountCreateDTO;
import com.dolph.DolphBank.dto.AccountDTO;
import com.dolph.DolphBank.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createBankAccount(@RequestBody AccountCreateDTO accountCreateDTO){
        accountService.save(accountCreateDTO);
        return new ResponseEntity<>("Request for account created.", HttpStatus.OK);
    }



}
