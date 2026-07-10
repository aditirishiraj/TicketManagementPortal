package com.bank.ticket_management.dto;

import com.bank.ticket_management.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketHistoryDTO {

    private Status oldStatus;

    private Status newStatus;

    private String updatedBy;

    private LocalDateTime updatedAt;

}