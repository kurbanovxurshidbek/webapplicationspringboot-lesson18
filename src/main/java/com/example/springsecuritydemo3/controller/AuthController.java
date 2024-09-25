package com.example.springsecuritydemo3.controller;

import com.example.springsecuritydemo3.model.dto.CustomResponse;
import com.example.springsecuritydemo3.model.dto.SignInRequest;
import com.example.springsecuritydemo3.model.dto.SignUpRequest;
import com.example.springsecuritydemo3.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public CustomResponse signUp(@RequestBody SignUpRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/signin")
    public CustomResponse signIn(@RequestBody SignInRequest request) {
        return authService.signin(request);
    }
}