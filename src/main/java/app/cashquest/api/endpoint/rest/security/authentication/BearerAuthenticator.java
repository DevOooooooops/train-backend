package app.cashquest.api.endpoint.rest.security.authentication;

import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class BearerAuthenticator implements Function<String, String> {
  @Override
  public String apply(String token) {
    return token;
  }
}
