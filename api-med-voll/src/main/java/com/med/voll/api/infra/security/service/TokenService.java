package com.med.voll.api.infra.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.med.voll.api.model.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
	@Value("${api.security.token.secret:12345678}")
	private String secret;
	
	public String getToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("API Voll.med")
					.withSubject(user.getLogin())
					.withExpiresAt(expirationDate())
					.sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar o token jwt", exception);
		}
	}

	private Instant expirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

    public Object getUser(String token){
		try {
			var algoritimo = Algorithm.HMAC256(secret);
			return JWT.require(algoritimo)
					.withIssuer("API Voll.med")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException exception){
			throw new RuntimeException("Token inválido");
		}
    }
}
