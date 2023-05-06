package textbasedgame.finalproject.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${game.jwtSecret}")
    private String jwtSecret;

    @Value("${game.jwtExpirationMs}")
    private int expirationMs;


    public String generateToken(Authentication authentication){

        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + this.expirationMs))
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();

    }


    public boolean validateJwtToken(String authToken){

        try {
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            log.debug("Invalid JWT signature: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.debug("JWT not supported: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.debug("JWT is malformed: {} ", e.getMessage());
        } catch (SignatureException e) {
            log.debug("Invalid signature for JWT : {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("Claim set is empty {}", e.getMessage());
        }

        return false;

    }


    public String extractUsernameFromJwt(String authToken){

        return Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(authToken).getBody().getSubject();

    }

}
