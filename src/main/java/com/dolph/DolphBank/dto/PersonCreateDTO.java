package com.dolph.DolphBank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonCreateDTO {

    private String name;

    private String surname;

    private Date birthDate;

    private String email;

    private String phone;

    private String address;

    private String username;

    private List<String> roles;
}
