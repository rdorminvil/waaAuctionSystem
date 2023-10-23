package edu.miu.waa.waaauctionsystem.models.authentication;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
