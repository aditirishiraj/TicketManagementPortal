package com.bank.ticket_management.service;

import com.bank.ticket_management.dto.LoginRequest;
import com.bank.ticket_management.dto.LoginResponse;
import com.bank.ticket_management.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    LoginResponse login(LoginRequest loginRequest);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

}