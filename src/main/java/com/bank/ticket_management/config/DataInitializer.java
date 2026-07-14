package com.bank.ticket_management.config;

import com.bank.ticket_management.entity.Client;
import com.bank.ticket_management.entity.Ticket;
import com.bank.ticket_management.entity.User;
import com.bank.ticket_management.enums.Category;
import com.bank.ticket_management.enums.Priority;
import com.bank.ticket_management.enums.Role;
import com.bank.ticket_management.enums.Status;
import com.bank.ticket_management.repository.ClientRepository;
import com.bank.ticket_management.repository.TicketRepository;
import com.bank.ticket_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.count() == 0) {

            userRepository.save(User.builder()
                    .firstName("Aditi").lastName("Sharma")
                    .email("aditi@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .role(Role.REQUESTOR).active(true)
                    .build());

            userRepository.save(User.builder()
                    .firstName("Rahul").lastName("Verma")
                    .email("rahul@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .role(Role.REQUESTOR).active(true)
                    .build());

            userRepository.save(User.builder()
                    .firstName("Priya").lastName("Singh")
                    .email("priya@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .role(Role.SUPPORT_ENGINEER).active(true)
                    .build());

            userRepository.save(User.builder()
                    .firstName("Arjun").lastName("Kumar")
                    .email("arjun@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .role(Role.SUPPORT_ENGINEER).active(true)
                    .build());

            userRepository.save(User.builder()
                    .firstName("Support").lastName("Engineer")
                    .email("support@bank.com")
                    .password(passwordEncoder.encode("support123"))
                    .role(Role.SUPPORT_ENGINEER).active(true)
                    .build());
        }

        if (clientRepository.count() == 0) {

            clientRepository.save(Client.builder().clientName("SBI").description("State Bank of India").build());
            clientRepository.save(Client.builder().clientName("HDFC").description("HDFC Bank").build());
            clientRepository.save(Client.builder().clientName("ICICI").description("ICICI Bank").build());
            clientRepository.save(Client.builder().clientName("Axis Bank").description("Axis Bank").build());
        }

        if (ticketRepository.count() == 0) {

            User aditi = userRepository.findByEmail("aditi@gmail.com").orElseThrow();
            User rahul = userRepository.findByEmail("rahul@gmail.com").orElseThrow();
            User priya = userRepository.findByEmail("priya@gmail.com").orElseThrow();

            Client sbi = clientRepository.findAll().stream()
                    .filter(c -> c.getClientName().equals("SBI")).findFirst().orElseThrow();
            Client hdfc = clientRepository.findAll().stream()
                    .filter(c -> c.getClientName().equals("HDFC")).findFirst().orElseThrow();

            ticketRepository.save(Ticket.builder()
                    .title("Unable to reset net banking password")
                    .description("User is locked out after 3 failed OTP attempts and needs a manual reset.")
                    .category(Category.LOGIN)
                    .priority(Priority.HIGH)
                    .status(Status.OPEN)
                    .client(sbi)
                    .createdBy(aditi)
                    .build());

            ticketRepository.save(Ticket.builder()
                    .title("Transaction shows debited but not credited")
                    .description("Fund transfer of ₹5000 was debited but not credited to the beneficiary account.")
                    .category(Category.TRANSFER)
                    .priority(Priority.CRITICAL)
                    .status(Status.IN_PROGRESS)
                    .client(hdfc)
                    .createdBy(rahul)
                    .assignedTo(priya)
                    .build());

            ticketRepository.save(Ticket.builder()
                    .title("Request to update registered mobile number")
                    .description("Customer wants to update their mobile number linked to the account for OTP delivery.")
                    .category(Category.ACCOUNT)
                    .priority(Priority.LOW)
                    .status(Status.OPEN)
                    .client(sbi)
                    .createdBy(aditi)
                    .build());
        }
    }
}