package edu.miu.waa.waaauctionsystem.controllers.authentication;

import edu.miu.waa.waaauctionsystem.libs.ResponseHandler;
import edu.miu.waa.waaauctionsystem.models.User;
import edu.miu.waa.waaauctionsystem.models.authentication.AuthenticationRequest;
import edu.miu.waa.waaauctionsystem.models.authentication.AuthenticationResponse;
import edu.miu.waa.waaauctionsystem.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final ResponseHandler responseHandler;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request){
        //System.out.println(request);
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthenticationResponse response = new AuthenticationResponse(user, accessToken);
            return ResponseEntity.ok(response);
        }catch (BadCredentialsException e){
            return responseHandler.response(null, "Wrong email or password", HttpStatus.BAD_REQUEST);
        }
    }
}
