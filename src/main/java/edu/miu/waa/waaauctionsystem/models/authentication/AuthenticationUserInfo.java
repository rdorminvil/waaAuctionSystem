package edu.miu.waa.waaauctionsystem.models.authentication;

import edu.miu.waa.waaauctionsystem.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUserInfo {
    public static User getUser(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        return  (User) auth.getPrincipal();
    }
}
