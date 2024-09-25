package com.example.springsecuritydemo3.service;

import com.example.springsecuritydemo3.model.domain.User;
import com.example.springsecuritydemo3.model.dto.CustomResponse;
import com.example.springsecuritydemo3.model.dto.SignInRequest;
import com.example.springsecuritydemo3.model.dto.SignUpRequest;
import com.example.springsecuritydemo3.model.security.UserPrinciple;
import com.example.springsecuritydemo3.repository.RoleRepository;
import com.example.springsecuritydemo3.repository.UserRepository;
import com.example.springsecuritydemo3.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public CustomResponse signup(SignUpRequest request) {
        var firstName = request.getFirstName();
        var lastName = request.getLastName();
        var email = request.getEmail();
        var password = passwordEncoder.encode(request.getPassword());

        var user = new User(firstName, lastName, email, password);

        var role  = roleRepository.findById(1L).get();
        user.getRoles().add(role);

        userService.save(user);
        //var jwt = jwtProvider.generateToken(user);
        return CustomResponse.ok("Signed up successfully");
    }

    public CustomResponse signin(SignInRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            //var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
            UserPrinciple userPrinciple = userRepository.findByEmail(request.getEmail()).map(UserPrinciple::from).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));


            var jwt = jwtProvider.generateToken(userPrinciple);

            return CustomResponse.ok(jwt);
        } catch (BadCredentialsException e) {
            return CustomResponse.error(e.getMessage());
        }
    }
}
