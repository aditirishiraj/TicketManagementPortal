package com.bank.ticket_management.repository;

import com.bank.ticket_management.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}