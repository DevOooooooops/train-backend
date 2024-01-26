package app.cashquest.api.endpoint.rest.security.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JwtConf {
  private final String secret;
  private final long expirationTime;
  private final String signatureAlgorithm;

  public JwtConf(
      @Value("${jwt.secret}") String secret,
      @Value("${jwt.expiration.time}") long expirationTime,
      @Value("${jwt.algorithm}") String signatureAlgorithm) {
    this.secret = secret;
    this.expirationTime = expirationTime;
    this.signatureAlgorithm = signatureAlgorithm;
  }
}
