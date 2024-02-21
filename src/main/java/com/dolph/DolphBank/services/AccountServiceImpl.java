package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.AccountCreateDTO;
import com.dolph.DolphBank.dto.AccountDTO;
import com.dolph.DolphBank.entites.Account;
import com.dolph.DolphBank.entites.Client;
import com.dolph.DolphBank.entites.Person;
import com.dolph.DolphBank.entites.StatusType;
import com.dolph.DolphBank.repositories.AccountRepository;
import com.dolph.DolphBank.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private ModelMapper mapper;

    private AccountRepository accountRepository;

    private PersonRepository personRepository;

    @Override
    public void save(AccountCreateDTO accountCreateDTO) {

        Account account = Account.builder()
                .accountType(accountCreateDTO.getAccountType())
                .bundleType(accountCreateDTO.getBundleType())
                .statusType(StatusType.NEAKTIVAN)
                .dateOfCreation(new Date())
                .owner((Client) findCurrentUser())
                .build();

        accountRepository.save(account);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        Client currentUser = (Client) findCurrentUser();
        List<Account> accounts = accountRepository.findAllByOwner(currentUser);
        return accounts.stream().map(account -> mapper.map(account, AccountDTO.class)).toList();
    }

    public Person findCurrentUser() {
        return personRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }
}
