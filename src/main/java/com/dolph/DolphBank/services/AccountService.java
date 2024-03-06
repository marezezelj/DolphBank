package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.AccountCreateDTO;
import com.dolph.DolphBank.dto.AccountDTO;
import com.dolph.DolphBank.dto.AccountRequestDTO;

import java.util.Date;
import java.util.List;

public interface AccountService {

    void save(AccountCreateDTO accountCreateDTO);

    List<AccountDTO> getAllAccounts();

    List<AccountRequestDTO> getAllRequests();

    String openAccount(Long id, Long employeeId, Date date);
}
