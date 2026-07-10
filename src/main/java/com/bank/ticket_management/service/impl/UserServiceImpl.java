package com.bank.ticket_management.service.impl;

import com.bank.ticket_management.dto.LoginRequest;
import com.bank.ticket_management.dto.LoginResponse;
import com.bank.ticket_management.dto.UserDTO;
import com.bank.ticket_management.entity.User;
import com.bank.ticket_management.exception.InvalidCredentialsException;
import com.bank.ticket_management.exception.UserNotFoundException;
import com.bank.ticket_management.repository.UserRepository;
import com.bank.ticket_management.service.UserService;
import com.bank.ticket_management.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + loginRequest.getEmail()));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user);

        return LoginResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .message("Login Successful")
                .token(token)
                .build();
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public UserDTO getUserById(Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<UserDTO> getAllUsers() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}