package com.example.firstprojectspringboot.service;

import com.example.firstprojectspringboot.dto.LoginRequestDto;
import com.example.firstprojectspringboot.dto.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(
            LoginRequestDto requestDto);
}