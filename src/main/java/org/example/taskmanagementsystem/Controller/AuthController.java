package org.example.taskmanagementsystem.Controller;

import org.example.taskmanagementsystem.DTOs.JWTAuthResponse;
import org.example.taskmanagementsystem.DTOs.UserDTO;
import org.example.taskmanagementsystem.Services.AuthService.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

      private AuthService userService;

      @Autowired
    public AuthController(AuthService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
      public ResponseEntity Register(@RequestBody UserDTO userDTO)
      {
          return ResponseEntity.ok(userService.signUp(userDTO.getUsername(), userDTO.getPassword()));
      }

      @PostMapping("/login")
      public ResponseEntity Login(@RequestBody UserDTO userDTO) {

          String token = userService.login(userDTO.getUsername(),userDTO.getPassword());
          JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
          jwtAuthResponse.setAccesstoken(token);
           return ResponseEntity.ok(jwtAuthResponse);
      }


}
