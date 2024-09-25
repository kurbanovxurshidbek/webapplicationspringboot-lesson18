package com.example.springsecuritydemo3.controller;

import com.example.springsecuritydemo3.model.dto.CustomResponse;
import com.example.springsecuritydemo3.model.security.UserPrinciple;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    // Solution-1 using @AuthenticationPrincipal
    @GetMapping("/me")
    public CustomResponse userMe(@AuthenticationPrincipal UserPrinciple userPrinciple) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var authorities = auth.getAuthorities();
        return CustomResponse.ok("ONLY users can see this - " + userPrinciple.getUsername() + " : " + authorities);
    }
}
