package app.cashquest.api.endpoint.rest.mapper;

import app.cashquest.api.endpoint.rest.model.CreateUser;
import app.cashquest.api.endpoint.rest.model.CreatedUser;
import app.cashquest.api.endpoint.rest.model.CrupdateUser;
import app.cashquest.api.endpoint.rest.model.Income;
import app.cashquest.api.repository.model.User;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static app.cashquest.api.service.UserService.checkLevel;
import static java.util.UUID.randomUUID;

@Component
@AllArgsConstructor
public class UserMapper {
  private final PasswordEncoder passwordEncoder;

  public User toDomain(CreateUser user){
    return User.builder()
        .username(user.getUsername())
        .password(passwordEncoder.encode(user.getPassword()))
        .birthdate(LocalDate.from(Objects.requireNonNull(user.getBirthDate())))
        .build();
  }

  public CreatedUser toRest(User domain){
    return new CreatedUser()
        .username(domain.getUsername())
        .birthDate(LocalDate.from(domain.getBirthdate()));
  }

  public User toDomain(CrupdateUser user){
    return User.builder()
        .id(randomUUID().toString())
        .username(Objects.requireNonNull(user.getUser()).getUsername())
        .birthdate(LocalDate.from(Objects.requireNonNull(user.getUser().getBirthDate())))
        .build();
  }

  public app.cashquest.api.endpoint.rest.model.User domainToRest(User user){
    app.cashquest.api.repository.model.Income income = user.getIncome();
    return new app.cashquest.api.endpoint.rest.model.User()
//        TODO: calculate balance based on income and outcome
        .balance(income.getAmount())
        .income(new Income().amount(income.getAmount())
                .earningFrequency(income.getEarningFrequency()))
        .user(toRest(user))
        .level(checkLevel());
  }
}
