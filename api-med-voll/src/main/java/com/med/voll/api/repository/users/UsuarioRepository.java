package com.med.voll.api.repository.users;

import com.med.voll.api.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}
