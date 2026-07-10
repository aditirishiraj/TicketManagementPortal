package com.bank.ticket_management.dto;

import com.bank.ticket_management.enums.Category;
import com.bank.ticket_management.enums.Priority;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketRequest {

    private String title;
    private String description;
    private Category category;
    private Priority priority;

    private Long clientId;

    private Long createdBy;

}