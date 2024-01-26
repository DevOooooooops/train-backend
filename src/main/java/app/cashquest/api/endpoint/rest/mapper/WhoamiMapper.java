package app.cashquest.api.endpoint.rest.mapper;

import app.cashquest.api.endpoint.rest.model.Whoami;
import app.cashquest.api.endpoint.rest.security.model.Principal;
import org.springframework.stereotype.Component;

@Component
public class WhoamiMapper {
  public Whoami toRest(Principal principal) {
    return new Whoami()
        .id(principal.getUser().getId())
        .username(principal.getUser().getUsername())
        .birthDate(principal.getUser().getBirthdate().toString());
  }
}
