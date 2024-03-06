package com.dolph.DolphBank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreateDTO {

    private Long id;

    private String jobRole;

    private String department;
}
