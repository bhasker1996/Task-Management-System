package org.example.taskmanagementsystem.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Register REST API",
            description = "Register REST API is used to SignUp as a user "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"

    )
    @PostMapping("/register")
      public ResponseEntity Register(@RequestBody UserDTO userDTO)
      {
          return ResponseEntity.ok(userService.signUp(userDTO.getUsername(), userDTO.getPassword()));
      }


    @Operation(
            summary = "Login REST API",
            description = "Login REST API is used to SignIn as a user "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"

    )
      @PostMapping("/login")
      public ResponseEntity Login(@RequestBody UserDTO userDTO) {

          String token = userService.login(userDTO.getUsername(),userDTO.getPassword());
          JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
          jwtAuthResponse.setAccesstoken(token);
           return ResponseEntity.ok(jwtAuthResponse);
      }


}
