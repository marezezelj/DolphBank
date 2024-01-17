package com.dolph.DolphBank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonCreateDTO {

    private String email;

    private String username;

    private List<String> roles;
}
