package com.dolph.DolphBank.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonUpdateDTO {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String phoneNumber;

    private String address;

}
