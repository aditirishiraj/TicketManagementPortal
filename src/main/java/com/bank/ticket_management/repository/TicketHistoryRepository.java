package com.bank.ticket_management.repository;

import com.bank.ticket_management.entity.Ticket;
import com.bank.ticket_management.entity.TicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {

    List<TicketHistory> findByTicket(Ticket ticket);

}