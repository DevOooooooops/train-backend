package app.cashquest.api.endpoint.rest.security.authentication;

import app.cashquest.api.endpoint.rest.security.utils.JwtComponent;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BearerAuthenticator implements Function<String, String> {
  private final JwtComponent jwtComponent;

  @Override
  public String apply(String token) {
    return jwtComponent.validateToken(token);
  }
}
