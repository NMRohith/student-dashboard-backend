package com.example.firstprojectspringboot.service.impl;

import com.example.firstprojectspringboot.dto.LoginRequestDto;
import com.example.firstprojectspringboot.dto.LoginResponseDto;
import com.example.firstprojectspringboot.entity.User;
import com.example.firstprojectspringboot.exception.InvalidCredentialsException;
import com.example.firstprojectspringboot.repository.UserRepository;
import com.example.firstprojectspringboot.security.JwtService;
import com.example.firstprojectspringboot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {

        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(
                requestDto.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole());

        return LoginResponseDto.builder()
                .token(token)
                .build();
    }
}