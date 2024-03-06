package com.dolph.DolphBank.services;

import com.dolph.DolphBank.dto.AccountCreateDTO;
import com.dolph.DolphBank.dto.AccountDTO;
import com.dolph.DolphBank.dto.AccountRequestDTO;
import com.dolph.DolphBank.entites.*;
import com.dolph.DolphBank.exceptions.NotFoundException;
import com.dolph.DolphBank.repositories.AccountRepository;
import com.dolph.DolphBank.repositories.EmployeeRepository;
import com.dolph.DolphBank.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private ModelMapper mapper;

    private AccountRepository accountRepository;

    //@Value("${bank.identification.number}")
    private static String bic = "143";

    //@Value("${bank.office.number}")
    private static String officeId = "115";

    private PersonRepository personRepository;

    private EmployeeRepository employeeRepository;

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

    @Override
    public List<AccountRequestDTO> getAllRequests() {
        List<Account> accounts = accountRepository.findAllByStatusType(StatusType.NEAKTIVAN).orElseThrow(() -> new NotFoundException("No requests found"));

        return accounts.stream().map(account -> mapper.map(account, AccountRequestDTO.class)).toList();
    }

    @Override
    public String openAccount(Long id, Long employeeId, Date date) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account not found"));
        account.setStatusType(StatusType.AKTIVAN);
        account.setAccountNumber(generateAccountNumber(bic));
        account.setDateOfExpiration(setDateOfExpiration(date));
        account.setBalance(0L);
        account.setAvailableBalance(0L);
        //account.setResponsibleEmployee(employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found")));
        account.setMaintanceFee(200L);

        accountRepository.save(account);

        return "Account opened successfully";
    }

    //TODO implement this method
    private String generateAccountNumber(String bic) {
        Random random = new Random();
        int accountNum = random.nextInt(900000000) + 100000000;

        String accountNumber = bic + officeId + accountNum + "182500";

        BigInteger bigIntegerAccountNumber = new BigInteger(accountNumber);
        BigInteger reminder = bigIntegerAccountNumber.mod(new BigInteger("97"));
        int controlNumber = (int) (98 - reminder.intValue());

        String controlNumberString = controlNumber <10 ? "0" + controlNumber : String.valueOf(controlNumber);

        String finalAccountNumber = bic + officeId + controlNumberString + accountNum;

        return finalAccountNumber;
    }

    private Date setDateOfExpiration(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 10);

        return calendar.getTime();
    }

    public Person findCurrentUser() {
        return personRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }
}
