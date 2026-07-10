package com.bank.ticket_management.service;

import com.bank.ticket_management.dto.LoginRequest;
import com.bank.ticket_management.dto.LoginResponse;
import com.bank.ticket_management.dto.UserDTO;

import java.util.List;

public interface UserService {

    LoginResponse login(LoginRequest loginRequest);

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

}