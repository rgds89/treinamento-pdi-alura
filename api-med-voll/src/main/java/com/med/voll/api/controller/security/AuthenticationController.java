package com.med.voll.api.controller.security;

import com.med.voll.api.dto.security.AuthenticationDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getPassword()));
        return ResponseEntity.ok().build();
    }
}
