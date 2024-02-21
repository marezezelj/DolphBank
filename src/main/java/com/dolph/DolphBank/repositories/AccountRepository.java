package com.dolph.DolphBank.repositories;

import com.dolph.DolphBank.entites.Account;
import com.dolph.DolphBank.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByOwner(Client client);
}
