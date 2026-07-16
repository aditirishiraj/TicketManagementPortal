package com.bank.ticket_management.controller;

import com.bank.ticket_management.dto.CommentDTO;
import com.bank.ticket_management.dto.TicketHistoryDTO;
import com.bank.ticket_management.dto.TicketRequest;
import com.bank.ticket_management.dto.TicketResponse;
import com.bank.ticket_management.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TicketResponse> createTicket(

            @RequestPart("ticket")
            @Valid TicketRequest request,

            @RequestPart(value = "file", required = false)
            MultipartFile file) {

        return new ResponseEntity<>(
                ticketService.createTicket(request, file),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {

        return ResponseEntity.ok(
                ticketService.getAllTickets()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ticketService.getTicketById(id)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketResponse>> getTicketsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                ticketService.getTicketsByUser(userId)
        );
    }

    @PutMapping("/{ticketId}/assign/{engineerId}")
    public ResponseEntity<TicketResponse> assignTicket(
            @PathVariable Long ticketId,
            @PathVariable Long engineerId) {

        return ResponseEntity.ok(
                ticketService.assignTicket(ticketId, engineerId)
        );
    }

    @PutMapping("/{ticketId}/status/{status}")
    public ResponseEntity<TicketResponse> updateTicketStatus(
            @PathVariable Long ticketId,
            @PathVariable String status) {

        return ResponseEntity.ok(
                ticketService.updateTicketStatus(ticketId, status)
        );
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentDTO> addComment(
            @Valid @RequestBody CommentDTO commentDTO) {

        return new ResponseEntity<>(
                ticketService.addComment(commentDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{ticketId}/comments")
    public ResponseEntity<List<CommentDTO>> getComments(
            @PathVariable Long ticketId) {

        return ResponseEntity.ok(
                ticketService.getCommentsByTicket(ticketId)
        );
    }

    @GetMapping("/{ticketId}/history")
    public ResponseEntity<List<TicketHistoryDTO>> getHistory(
            @PathVariable Long ticketId) {

        return ResponseEntity.ok(
                ticketService.getTicketHistory(ticketId)
        );
    }
}