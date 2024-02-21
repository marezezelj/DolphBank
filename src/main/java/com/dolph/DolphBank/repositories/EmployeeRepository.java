package com.dolph.DolphBank.repositories;

import com.dolph.DolphBank.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query(value = "INSERT INTO zaposleni (id_zaposleni, departman, pozicija) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void insertEmployee(Long id, String department, String jobRole);
}
