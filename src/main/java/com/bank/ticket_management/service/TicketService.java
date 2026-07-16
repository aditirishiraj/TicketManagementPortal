package com.bank.ticket_management.service;

import com.bank.ticket_management.dto.CommentDTO;
import com.bank.ticket_management.dto.TicketHistoryDTO;
import com.bank.ticket_management.dto.TicketRequest;
import com.bank.ticket_management.dto.TicketResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TicketService {

    TicketResponse createTicket(TicketRequest request, MultipartFile file);

    List<TicketResponse> getAllTickets();

    List<TicketResponse> getTicketsByUser(Long userId);

    TicketResponse getTicketById(Long id);

    TicketResponse assignTicket(Long ticketId, Long engineerId);

    TicketResponse updateTicketStatus(Long ticketId, String status);

    CommentDTO addComment(CommentDTO commentDTO);

    List<CommentDTO> getCommentsByTicket(Long ticketId);

    List<TicketHistoryDTO> getTicketHistory(Long ticketId);

}