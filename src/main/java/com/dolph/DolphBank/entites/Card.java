package com.dolph.DolphBank.entites;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="kartica")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_kartica")
    private Long id;

    @Column(name="broj_kartice")
    private int cardNumber;

    @Column(name="vrsta_kartice")
    private CardType cardType;

    @Column(name="datum_kreiranja")
    private Date creationDate;

    @Column(name="datum_isteka")
    private Date expirationDate;

    @Column(name="CVV")
    private int cvv;

    @Column(name="limit")
    private Long limit;

    @Column(name="status")
    private StatusType status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_racun")
    private Account account;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
