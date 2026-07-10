package com.bank.ticket_management.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {

    private Long ticketId;

    private String message;

    private Long userId;

    private LocalDateTime commentedAt;

}