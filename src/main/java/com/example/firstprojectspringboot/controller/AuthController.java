package com.example.firstprojectspringboot.controller;

import com.example.firstprojectspringboot.dto.LoginRequestDto;
import com.example.firstprojectspringboot.dto.LoginResponseDto;
import com.example.firstprojectspringboot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody
            LoginRequestDto requestDto) {

        return authService.login(
                requestDto);
    }
}