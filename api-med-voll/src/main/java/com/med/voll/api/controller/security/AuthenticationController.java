package com.med.voll.api.controller.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.med.voll.api.dto.security.AuthenticationDTO;
import com.med.voll.api.infra.security.dto.TokenDataJWT;
import com.med.voll.api.infra.security.service.TokenService;
import com.med.voll.api.model.users.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> authenticate(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(),
				authenticationDTO.getPassword());
		var authentication = authenticationManager.authenticate(authenticationToken);
		var tokenJWT = tokenService.getToken((User) authentication.getPrincipal());
		return ResponseEntity.ok(new TokenDataJWT(tokenJWT));
	}
}
