package com.dolph.DolphBank.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="transakcija")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_transakcija")
    private Long id;

    @Column(name="naziv_primaoca")
    private String recipientName;

    @Column(name="racun_primaoca")
    private String recipientAccountNumber;

    @Column(name="iznos")
    private Long amount;

    @Column(name="poziv_na_broj")
    private int referenceNumber;

    @Column(name="sifra_placanja")
    private int paymentCode;

    @Column(name="model")
    private byte model;

    @Column(name="svrha")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kartica")
    private Card card;
}
