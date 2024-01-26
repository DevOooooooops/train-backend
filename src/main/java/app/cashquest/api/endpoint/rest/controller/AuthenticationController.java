package app.cashquest.api.endpoint.rest.controller;

import app.cashquest.api.endpoint.rest.model.AuthUser;
import app.cashquest.api.endpoint.rest.model.AuthenticatedUser;
import app.cashquest.api.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {
  private final AuthenticationService service;

  @PostMapping("/token")
  public AuthenticatedUser authenticateUser(@RequestBody AuthUser user) {
    return service.authenticateUser(user);
  }
}
