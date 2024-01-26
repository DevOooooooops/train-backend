package app.cashquest.api.endpoint.rest.security.utils;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class JwtComponent {
  private final JwtConf conf;

  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + conf.getExpirationTime()))
        .signWith(HS256, conf.getSecret())
        .compact();
  }

  private String getUsernameFromToken(String token) {
    return Jwts.parser()
        .setSigningKey(conf.getSecret())
        .parseClaimsJwt(token)
        .getBody()
        .getSubject();
  }

  public String validateToken(String token) {
    String username = getUsernameFromToken(token);
    Claims claims = Jwts.parser().setSigningKey(conf.getSecret()).parseClaimsJwt(token).getBody();
    boolean isTokenExpired = claims.getExpiration().before(new Date());
    if (!isTokenExpired) {
      return username;
    }
    return null;
  }
}
