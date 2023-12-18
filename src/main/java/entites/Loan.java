package entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="kredit")
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_kredit")
    private Long id;

    @Column(name="naziv")
    private String name;

    @Column(name="iznos_kredita")
    private Long amount;

    @Column(name="period_otplate")
    private int amountOfMonths;

    @Column(name="nks")
    private Long nks;

    @Column(name="eks")
    private Long eks;

    @Column(name="datum_ugovaranja")
    private Date dateOfCreation;

    @Column(name="datum_dospeca")
    private Date dateOfEnd;

    @Column(name="iznos_rate")
    private Long rateAmount;

    @Column(name="datum_sl_rate")
    private Date nextRateDate;

    @Column(name="dug")
    private Long remainingAmount;

    @Column(name="status")
    private StatusType status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_racun")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zaposleni")
    private Employee responsibleEmployee;
}
