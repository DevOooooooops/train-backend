package app.cashquest.api.endpoint.rest.mapper;

import app.cashquest.api.endpoint.rest.model.CreateUser;
import app.cashquest.api.repository.model.User;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Component
@AllArgsConstructor
public class UserMapper {
  public User toDomain(CreateUser user){
    return User.builder()
        .id(randomUUID().toString())
        .username(user.getUsername())
        .password(user.getPassword())
        .birthdate(LocalDate.from(Objects.requireNonNull(user.getBirthDate())))
        .build();
  }

  public CreateUser toRest(User domain){
    return new CreateUser()
        .username(domain.getUsername())
        .birthDate(Instant.from(domain.getBirthdate()))
        .password(domain.getPassword());
  }
}
