package com.bank.ticket_management.repository;

import com.bank.ticket_management.entity.Ticket;
import com.bank.ticket_management.entity.User;
import com.bank.ticket_management.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByCreatedBy(User user);

    List<Ticket> findByAssignedTo(User user);

    List<Ticket> findByStatus(Status status);

    List<Ticket> findByAssignedToAndStatus(User user, Status status);

}