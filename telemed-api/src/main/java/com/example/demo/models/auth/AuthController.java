package com.example.demo.models.auth;

import com.example.demo.models.auth.dtos.LoginDto;
import com.example.demo.models.auth.dtos.LoginResponseDto;
import com.example.demo.models.auth.dtos.RegisterDto;
import com.example.demo.models.user.dtos.GetUserDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Operations related to authentication")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<GetUserDto> registerUser(@RequestBody RegisterDto registerDTO) {
        GetUserDto registeredUser = authService.registerUser(registerDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginDto loginDTO) {
        try {
            LoginResponseDto loginResponse = authService.loginUser(loginDTO);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
    }
}
