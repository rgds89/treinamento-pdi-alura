package com.med.voll.api.service.security;

import com.med.voll.api.infra.security.service.TokenService;
import com.med.voll.api.repository.users.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UsuarioRepository  usuarioRepository;
    private final TokenService tokenService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }
    
    public void authenticate(String token) {
    	var subject = tokenService.getUser(token.replace("Bearer ", "")).toString();
        var user = loadUserByUsername(subject);
        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());           
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
