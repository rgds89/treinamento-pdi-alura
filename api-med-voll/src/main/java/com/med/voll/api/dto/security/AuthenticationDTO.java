package com.med.voll.api.dto.security;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationDTO {
    private String login;
    private String password;
}
