package com.bank.ticket_management.repository;

import com.bank.ticket_management.entity.Comment;
import com.bank.ticket_management.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTicket(Ticket ticket);

}