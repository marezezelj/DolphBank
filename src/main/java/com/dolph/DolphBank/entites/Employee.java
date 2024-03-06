package com.dolph.DolphBank.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "zaposleni")
@PrimaryKeyJoinColumn(name = "id_zaposleni")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Person {

    @Column(name = "pozicija")
    private String role;

    @Column(name = "departman")
    private String department;

    @OneToMany(mappedBy = "responsibleEmployee")
    private List<Account> responsibleAccounts;

    @OneToMany(mappedBy = "responsibleEmployee")
    private List<Loan> responsibleLoans;
}
