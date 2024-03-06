package com.dolph.DolphBank.repositories;

import com.dolph.DolphBank.entites.Account;
import com.dolph.DolphBank.entites.Client;
import com.dolph.DolphBank.entites.Employee;
import com.dolph.DolphBank.entites.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByOwner(Client client);

    Optional<List<Account>> findAllByStatusType(StatusType statusType);

    @Query("SELECT a FROM Account a WHERE a.responsibleEmployee.id = ?1")
    Optional<List<Account>> findAllByResponsibleEmployee(Long id);
}
