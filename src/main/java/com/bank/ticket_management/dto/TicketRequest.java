package com.bank.ticket_management.dto;

import com.bank.ticket_management.enums.Category;
import com.bank.ticket_management.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 100,
            message = "Title must be between 5 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000,
            message = "Description must be between 10 and 1000 characters")
    private String description;

    @NotNull(message = "Category is required")
    private Category category;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @NotNull(message = "Client is required")
    private Long clientId;

    @NotNull(message = "Created By is required")
    private Long createdBy;

    private String attachmentPath;
}