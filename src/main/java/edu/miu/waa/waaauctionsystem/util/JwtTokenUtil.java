package edu.miu.waa.waaauctionsystem.util;

import edu.miu.waa.waaauctionsystem.models.Product;
import edu.miu.waa.waaauctionsystem.models.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Slf4j
@Component
public class JwtTokenUtil {
    @Value("${auctionApp.jwt.secret}")
    private String SECRET_KEY;
    @Value("${auctionApp.jwt.expire.duration}")
    private long EXPIRE_DURATION;
    public String generateAccessToken(User user){
        return Jwts.builder()
                .setSubject(user.getId()+","+user.getEmail())
                .setIssuer("AU")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.ES512, SECRET_KEY)
                .compact();
    }
    public boolean validateAccessToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJwt(token);
            return true;
        }catch (MalformedJwtException | ExpiredJwtException e){
            log.error(e.getMessage());
        }
        return false;
    }
    public Claims parseClaims(String token){
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJwt(token).getBody();
    }
}
