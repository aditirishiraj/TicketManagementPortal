package com.bank.ticket_management.service;

import com.bank.ticket_management.dto.*;
import com.bank.ticket_management.dto.ForgotPasswordResponse;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    LoginResponse login(LoginRequest loginRequest);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    TicketResponse.ForgotPasswordResponse forgotPassword(UserDTO.ForgotPasswordRequest request);

    void resetPassword(ResetPasswordRequest request);

}