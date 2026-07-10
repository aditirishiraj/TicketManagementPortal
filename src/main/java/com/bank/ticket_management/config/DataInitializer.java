package com.bank.ticket_management.config;

import com.bank.ticket_management.entity.Client;
import com.bank.ticket_management.entity.User;
import com.bank.ticket_management.enums.Role;
import com.bank.ticket_management.repository.ClientRepository;
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
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.count() == 0) {

            userRepository.save(User.builder()
                    .firstName("Aditi")
                    .lastName("Sharma")
                    .email("aditi@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .role(Role.REQUESTOR)
                    .build());

            userRepository.save(User.builder()
                    .firstName("Rahul")
                    .lastName("Verma")
                    .email("rahul@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .role(Role.REQUESTOR)
                    .build());

            userRepository.save(User.builder()
                    .firstName("Priya")
                    .lastName("Singh")
                    .email("priya@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .role(Role.SUPPORT_ENGINEER)
                    .build());

            userRepository.save(User.builder()
                    .firstName("Arjun")
                    .lastName("Kumar")
                    .email("arjun@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .role(Role.SUPPORT_ENGINEER)
                    .build());
        }

        if (clientRepository.count() == 0) {

            clientRepository.save(Client.builder()
                    .clientName("SBI")
                    .description("State Bank of India")
                    .build());

            clientRepository.save(Client.builder()
                    .clientName("HDFC")
                    .description("HDFC Bank")
                    .build());

            clientRepository.save(Client.builder()
                    .clientName("ICICI")
                    .description("ICICI Bank")
                    .build());

            clientRepository.save(Client.builder()
                    .clientName("Axis Bank")
                    .description("Axis Bank")
                    .build());
        }
    }
}