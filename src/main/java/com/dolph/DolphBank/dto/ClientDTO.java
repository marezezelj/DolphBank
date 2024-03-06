package com.dolph.DolphBank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;

    private String name;

    private String surname;

    private Date dateOfBirth;

    private String email;

    private String username;

    private String phone;

    private String address;

    private Long rating;

}
