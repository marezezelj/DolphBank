package com.dolph.DolphBank.dto;

import com.dolph.DolphBank.entites.AccountType;
import com.dolph.DolphBank.entites.BundleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {

    private Long id;

    private ClientDTO client;

    private Date dateOfCreation;

    private AccountType accountType;

    private BundleType bundleType;

}
