package com.dolph.DolphBank.repositories;

import com.dolph.DolphBank.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Modifying
    @Query(value = "DELETE FROM klijent WHERE id = ?1", nativeQuery = true)
    void removeClient(Long id);
}
