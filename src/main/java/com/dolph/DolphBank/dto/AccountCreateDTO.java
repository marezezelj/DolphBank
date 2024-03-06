package com.dolph.DolphBank.dto;

import com.dolph.DolphBank.entites.AccountType;
import com.dolph.DolphBank.entites.BundleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateDTO {

    private AccountType accountType;

    private BundleType bundleType;
}
