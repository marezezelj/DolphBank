package com.dolph.DolphBank.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "klijent")
@PrimaryKeyJoinColumn(name = "id")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Person {

    @Column(name = "rejting")
    private Long creditRating;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

}
