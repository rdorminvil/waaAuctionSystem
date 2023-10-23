package edu.miu.waa.waaauctionsystem.filter;

import edu.miu.waa.waaauctionsystem.models.User;
import edu.miu.waa.waaauctionsystem.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            String authHeader=request.getHeader("Authorization");
            if(null==authHeader){
                filterChain.doFilter(request, response);
                return;
            }
            String token=authHeader.split(" ")[1];
            boolean result=jwtTokenUtil.validateAccessToken(token);
            if(!result){
                filterChain.doFilter(request, response);
                return;
            }
            setAuthenticationContext(token, request);
            filterChain.doFilter(request, response);
        }
        private void setAuthenticationContext(String token, HttpServletRequest request){
            UserDetails u = getUserDetails(token);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(u, null, null);
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }
        private UserDetails getUserDetails(String token) {
            User user= new User();
            Claims claims = jwtTokenUtil.parseClaims(token);
            String[] subject = claims.getSubject().split(",");
            user.setId(Long.parseLong(subject[0]));
            user.setEmail(subject[1]);
            return user;
    }
}
