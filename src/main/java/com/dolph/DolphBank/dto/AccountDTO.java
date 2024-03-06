package com.dolph.DolphBank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    private String accountNumber;

    private String accountType;

    private String bundleType;

    private Long balance;

    private Long availableBalance;

    private Date dateOfExpiration;
}
