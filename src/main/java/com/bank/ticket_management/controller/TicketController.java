package com.bank.ticket_management.controller;

import com.bank.ticket_management.dto.CommentDTO;
import com.bank.ticket_management.dto.TicketHistoryDTO;
import com.bank.ticket_management.dto.TicketRequest;
import com.bank.ticket_management.dto.TicketResponse;
import com.bank.ticket_management.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public TicketResponse createTicket(@RequestBody TicketRequest request) {
        return ticketService.createTicket(request);
    }

    @GetMapping
    public List<TicketResponse> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketResponse getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/user/{userId}")
    public List<TicketResponse> getTicketsByUser(@PathVariable Long userId) {
        return ticketService.getTicketsByUser(userId);
    }

    @PutMapping("/{ticketId}/assign/{engineerId}")
    public TicketResponse assignTicket(@PathVariable Long ticketId,
                                       @PathVariable Long engineerId) {
        return ticketService.assignTicket(ticketId, engineerId);
    }

    @PutMapping("/{ticketId}/status")
    public TicketResponse updateStatus(@PathVariable Long ticketId,
                                       @RequestParam String status) {
        return ticketService.updateTicketStatus(ticketId, status);
    }

    @PostMapping("/comments")
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO) {
        return ticketService.addComment(commentDTO);
    }

    @GetMapping("/{ticketId}/comments")
    public List<CommentDTO> getComments(@PathVariable Long ticketId) {
        return ticketService.getCommentsByTicket(ticketId);
    }

    @GetMapping("/{ticketId}/history")
    public List<TicketHistoryDTO> getHistory(@PathVariable Long ticketId) {
        return ticketService.getTicketHistory(ticketId);
    }
}