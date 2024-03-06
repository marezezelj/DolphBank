package com.dolph.DolphBank.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="racun")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="brojRacuna")
    private String accountNumber;

    @Column(name="stanje")
    private Long balance;

    @Column(name="raspolozivoStanje")
    private Long availableBalance;

    @Column(name="datumKreiranja")
    private Date dateOfCreation;

    @Column(name="datumIsteka")
    private Date dateOfExpiration;

    @Column(name="tip")
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType;

    @Column(name="status")
    @Enumerated(EnumType.ORDINAL)
    private StatusType statusType;

    @Column(name="vrstaPaketa")
    @Enumerated(EnumType.ORDINAL)
    private BundleType bundleType;

    @Column(name="kamatnaStopa")
    private Long interestRate;

    @Column(name="odrzavanje")
    private Long maintanceFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vlasnik")
    private Client owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bankar")
    private Employee responsibleEmployee;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Card> cards;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Loan> loans;

}
