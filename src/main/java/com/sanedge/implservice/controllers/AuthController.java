package com.sanedge.implservice.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.implservice.models.ERole;
import com.sanedge.implservice.models.Role;
import com.sanedge.implservice.models.User;
import com.sanedge.implservice.payload.request.LoginRequest;
import com.sanedge.implservice.payload.request.SignupRequest;
import com.sanedge.implservice.payload.response.JwtResponse;
import com.sanedge.implservice.payload.response.MessageResponse;
import com.sanedge.implservice.repository.RoleRepository;
import com.sanedge.implservice.repository.UserRepository;
import com.sanedge.implservice.security.jwt.JwtUtils;
import com.sanedge.implservice.security.services.UserDetailsImpl;
import com.sanedge.implservice.service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  AuthService authService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/login")
  public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    return this.authService.login(loginRequest);
  }

  @PostMapping("/register")
  public MessageResponse registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return this.authService.register(signUpRequest);
  }
}
