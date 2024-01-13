package com.dolph.DolphBank.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "klijent")
@PrimaryKeyJoinColumn(name = "id")
@Data
public class Client extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "rejting")
    private Long creditRating;

    @Column(name = "id_osoba")
    private Long idPerson;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

}
