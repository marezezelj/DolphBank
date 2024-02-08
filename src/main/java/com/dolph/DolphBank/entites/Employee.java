package com.dolph.DolphBank.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "zaposleni")
@PrimaryKeyJoinColumn(name = "id_osoba")
@Data
public class Employee extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_zaposleni")
    private Long id;

    @Column(name = "pozicija")
    private String role;

    @Column(name = "departman")
    private String department;

    /*@Column(name = "id_osoba")
    private Long idPerson;*/

    @OneToMany(mappedBy = "responsibleEmployee")
    private List<Account> responsibleAccounts;

    @OneToMany(mappedBy = "responsibleEmployee")
    private List<Loan> responsibleLoans;
}
