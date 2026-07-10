package com.bank.ticket_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {

    @NotNull(message = "Ticket Id is required")
    private Long ticketId;

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotBlank(message = "Comment cannot be empty")
    @Size(max = 500,
            message = "Comment cannot exceed 500 characters")
    private String message;

    private LocalDateTime commentedAt;
}