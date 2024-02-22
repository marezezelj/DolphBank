package com.dolph.DolphBank.repositories;

import com.dolph.DolphBank.dto.EmployeeDTO;
import com.dolph.DolphBank.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query(value = "INSERT INTO zaposleni (id_zaposleni, departman, pozicija) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void insertEmployee(Long id, String department, String jobRole);

    /*@Modifying
    @Query(value = "UPDATE zaposleni SET departman = ?2, pozicija = ?3 WHERE id_zaposleni = ?1", nativeQuery = true)
    void updateEmployee(Long id, String department, String jobRole);*/

    List<Employee> findAllByActive(boolean active);
}
