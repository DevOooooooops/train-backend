package app.cashquest.api.endpoint.rest.controller;

import app.cashquest.api.endpoint.rest.mapper.WhoamiMapper;
import app.cashquest.api.endpoint.rest.model.Whoami;
import app.cashquest.api.endpoint.rest.security.model.Principal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WhoamiController {
  private final WhoamiMapper mapper;

  @GetMapping("/whoami")
  public Whoami whoami(@AuthenticationPrincipal Principal principal) {
    return mapper.toRest(principal);
  }
}
