package com.bank.ticket_management.dto;

import com.bank.ticket_management.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String message;
    private String token;

}