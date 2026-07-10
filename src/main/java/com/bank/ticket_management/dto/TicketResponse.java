package com.bank.ticket_management.dto;

import com.bank.ticket_management.enums.Category;
import com.bank.ticket_management.enums.Priority;
import com.bank.ticket_management.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {

    private Long id;

    private String title;

    private String description;

    private Category category;

    private Priority priority;

    private Status status;

    private String attachmentPath;

    private LocalDateTime createdAt;

    private String clientName;

    private String createdBy;

    private String assignedTo;

}