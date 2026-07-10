package com.bank.ticket_management.service.impl;

import com.bank.ticket_management.dto.CommentDTO;
import com.bank.ticket_management.dto.TicketHistoryDTO;
import com.bank.ticket_management.dto.TicketRequest;
import com.bank.ticket_management.dto.TicketResponse;
import com.bank.ticket_management.entity.Client;
import com.bank.ticket_management.entity.Ticket;
import com.bank.ticket_management.entity.User;
import com.bank.ticket_management.enums.Status;
import com.bank.ticket_management.exception.ClientNotFoundException;
import com.bank.ticket_management.exception.TicketNotFoundException;
import com.bank.ticket_management.exception.UserNotFoundException;
import com.bank.ticket_management.repository.ClientRepository;
import com.bank.ticket_management.repository.CommentRepository;
import com.bank.ticket_management.repository.TicketHistoryRepository;
import com.bank.ticket_management.repository.TicketRepository;
import com.bank.ticket_management.repository.UserRepository;
import com.bank.ticket_management.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.ticket_management.entity.TicketHistory;
import com.bank.ticket_management.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TicketHistoryRepository ticketHistoryRepository;

    @Override
    public TicketResponse createTicket(TicketRequest request) {

        User user = userRepository.findById(request.getCreatedBy())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() ->
                        new ClientNotFoundException("Client not found"));

        Ticket ticket = Ticket.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .priority(request.getPriority())
                .status(Status.OPEN)
                .client(client)
                .createdBy(user)
                .assignedTo(null)
                .build();

        ticket = ticketRepository.save(ticket);

        return convertToResponse(ticket);
    }

    @Override
    public List<TicketResponse> getAllTickets() {

        return ticketRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }

    @Override
    public List<TicketResponse> getTicketsByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        return ticketRepository.findByCreatedBy(user)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }

    @Override
    public TicketResponse getTicketById(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new TicketNotFoundException("Ticket not found"));

        return convertToResponse(ticket);

    }

    private TicketResponse convertToResponse(Ticket ticket) {

        return TicketResponse.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .category(ticket.getCategory())
                .priority(ticket.getPriority())
                .status(ticket.getStatus())
                .attachmentPath(ticket.getAttachmentPath())
                .createdAt(ticket.getCreatedAt())
                .clientName(ticket.getClient().getClientName())
                .createdBy(
                        ticket.getCreatedBy().getFirstName()
                                + " "
                                + ticket.getCreatedBy().getLastName()
                )
                .assignedTo(
                        ticket.getAssignedTo() == null
                                ? null
                                : ticket.getAssignedTo().getFirstName()
                                  + " "
                                  + ticket.getAssignedTo().getLastName()
                )
                .build();

    }

    @Override
    public TicketResponse assignTicket(Long ticketId, Long engineerId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new TicketNotFoundException("Ticket not found"));

        User engineer = userRepository.findById(engineerId)
                .orElseThrow(() ->
                        new UserNotFoundException("Support Engineer not found"));

        ticket.setAssignedTo(engineer);

        ticket = ticketRepository.save(ticket);

        return convertToResponse(ticket);
    }

    @Override
    public TicketResponse updateTicketStatus(Long ticketId, String status) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new TicketNotFoundException("Ticket not found"));

        Status oldStatus = ticket.getStatus();

        Status newStatus = Status.valueOf(status.toUpperCase());

        ticket.setStatus(newStatus);

        ticket = ticketRepository.save(ticket);

        TicketHistory history = TicketHistory.builder()
                .oldStatus(oldStatus)
                .newStatus(newStatus)
                .ticket(ticket)
                .updatedBy(ticket.getAssignedTo())
                .build();

        ticketHistoryRepository.save(history);

        return convertToResponse(ticket);
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {

        Ticket ticket = ticketRepository.findById(commentDTO.getTicketId())
                .orElseThrow(() ->
                        new TicketNotFoundException("Ticket not found"));

        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        Comment comment = Comment.builder()
                .message(commentDTO.getMessage())
                .ticket(ticket)
                .user(user)
                .build();

        comment = commentRepository.save(comment);

        return CommentDTO.builder()
                .ticketId(ticket.getId())
                .userId(user.getId())
                .message(comment.getMessage())
                .commentedAt(comment.getCommentedAt())
                .build();
    }

    @Override
    public List<CommentDTO> getCommentsByTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new TicketNotFoundException("Ticket not found"));

        return commentRepository.findByTicket(ticket)
                .stream()
                .map(comment -> CommentDTO.builder()
                        .ticketId(ticket.getId())
                        .userId(comment.getUser().getId())
                        .message(comment.getMessage())
                        .commentedAt(comment.getCommentedAt())
                        .build())
                .toList();
    }

    @Override
    public List<TicketHistoryDTO> getTicketHistory(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new TicketNotFoundException("Ticket not found"));

        return ticketHistoryRepository.findByTicket(ticket)
                .stream()
                .map(history -> TicketHistoryDTO.builder()
                        .oldStatus(history.getOldStatus())
                        .newStatus(history.getNewStatus())
                        .updatedBy(
                                history.getUpdatedBy().getFirstName()
                                        + " "
                                        + history.getUpdatedBy().getLastName()
                        )
                        .updatedAt(history.getUpdatedAt())
                        .build())
                .toList();
    }

}