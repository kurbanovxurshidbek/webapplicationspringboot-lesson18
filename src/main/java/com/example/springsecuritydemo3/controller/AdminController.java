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
@RequestMapping("/api/admin")
public class AdminController {

    // Solution-2 using SecurityContextHolder
    @GetMapping("/me")
    public CustomResponse adminMe() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var userPrinciple = (UserPrinciple) auth.getPrincipal();  // Manually extract the principal
        var authorities = auth.getAuthorities();
        return CustomResponse.ok("ONLY admins can see this - "+ userPrinciple.email() + " : " + authorities);
    }
}
