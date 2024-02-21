package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.AccountCreateDTO;
import com.dolph.DolphBank.dto.AccountDTO;

import java.util.List;

public interface AccountService {

    void save(AccountCreateDTO accountCreateDTO);

    List<AccountDTO> getAllAccounts();
}
