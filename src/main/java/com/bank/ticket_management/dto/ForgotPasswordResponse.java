package com.bank.ticket_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForgotPasswordResponse {

    private String message;

    // Returned directly since the project has no email server configured.
    // Swap this out for an actual email send once SMTP is set up, and
    // stop returning the token in the response body.
    private String resetToken;
}