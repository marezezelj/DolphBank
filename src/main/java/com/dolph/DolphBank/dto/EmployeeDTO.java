package com.dolph.DolphBank.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String username;

    private String phone;

    private String role;

    private String department;

}
