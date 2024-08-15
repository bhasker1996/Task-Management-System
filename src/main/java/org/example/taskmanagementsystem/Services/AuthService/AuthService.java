package org.example.taskmanagementsystem.Services.AuthService;

import org.example.taskmanagementsystem.DTOs.UserDTO;

public interface AuthService {

    String login(String username, String password);
    String signUp(String username, String password);

}
