package app.cashquest.api.service;

import app.cashquest.api.endpoint.rest.model.AuthUser;
import app.cashquest.api.endpoint.rest.model.AuthenticatedUser;
import app.cashquest.api.endpoint.rest.security.exception.ForbiddenException;
import app.cashquest.api.endpoint.rest.security.utils.JwtComponent;
import app.cashquest.api.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
  private final JwtComponent jwtComponent;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public AuthenticatedUser authenticateUser(AuthUser user) {
    User currentUser = userService.findByUsername(user.getUsername());
    if (passwordEncoder.matches(user.getPassword(), currentUser.getPassword())) {
      return new AuthenticatedUser()
          .username(currentUser.getUsername())
          .bearerToken(jwtComponent.generateToken(currentUser.getUsername()));
    }
    throw new ForbiddenException("Incorrect credentials, please check.");
  }
}
