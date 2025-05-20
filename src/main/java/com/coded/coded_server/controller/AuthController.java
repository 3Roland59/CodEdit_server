package com.coded.coded_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coded.coded_server.dto.AuthRequestDto;
import com.coded.coded_server.dto.AuthResponseDto;
import com.coded.coded_server.dto.UserResponseDto;
import com.coded.coded_server.jwt.JwtService;
import com.coded.coded_server.mapper.UserMapper;
import com.coded.coded_server.model.User;
import com.coded.coded_server.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtService.generateToken(user);
            UserResponseDto userDto = UserMapper.toResponse(user);

            AuthResponseDto response = new AuthResponseDto();
            response.setToken(token);
            response.setUser(userDto);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
