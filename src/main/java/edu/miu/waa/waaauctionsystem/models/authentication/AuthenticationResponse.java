package edu.miu.waa.waaauctionsystem.models.authentication;

import edu.miu.waa.waaauctionsystem.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private User user;
    private String accessToken;

}
