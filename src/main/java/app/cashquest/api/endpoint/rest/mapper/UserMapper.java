package app.cashquest.api.endpoint.rest.mapper;

import static app.cashquest.api.endpoint.rest.model.TransactionType.INCOME;
import static app.cashquest.api.service.UserService.checkLevel;

import app.cashquest.api.endpoint.rest.model.CreateUser;
import app.cashquest.api.endpoint.rest.model.CreatedUser;
import app.cashquest.api.endpoint.rest.model.CrupdateUser;
import app.cashquest.api.endpoint.rest.model.Income;
import app.cashquest.api.endpoint.rest.model.Profile;
import app.cashquest.api.endpoint.rest.security.model.Principal;
import app.cashquest.api.repository.TransactionRepository;
import app.cashquest.api.repository.model.Transaction;
import app.cashquest.api.repository.model.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
  private final PasswordEncoder passwordEncoder;
  private final IncomeMapper incomeMapper;
  private final TransactionRepository transactionRepository;

  public User toDomain(CreateUser user) {
    return User.builder()
        .username(user.getUsername())
        .password(passwordEncoder.encode(user.getPassword()))
        .birthdate(LocalDate.from(Objects.requireNonNull(user.getBirthDate())))
        .build();
  }

  public CreatedUser toRest(User domain) {
    return new CreatedUser()
        .id(domain.getId())
        .username(domain.getUsername())
        .birthDate(LocalDate.from(domain.getBirthdate()));
  }

  public User toDomain(CrupdateUser user, Principal principal) {
    CreatedUser userPayload = user.getUser();
    Profile profile = user.getProfile();
    return User.builder()
        .id(principal.getUser().getId())
        .password(principal.getPassword())
        .username(Objects.requireNonNull(userPayload).getUsername())
        .birthdate(LocalDate.from(Objects.requireNonNull(userPayload.getBirthDate())))
        .income(incomeMapper.toDomain(Objects.requireNonNull(user.getIncome())))
        .firstName(profile.getFirstName())
        .lastName(profile.getLastName())
        .sex(profile.getSex())
        .build();
  }

  public app.cashquest.api.endpoint.rest.model.User domainToRest(User user) {
    app.cashquest.api.repository.model.Income income = user.getIncome();
    AtomicInteger balance = new AtomicInteger();
    List<Transaction> transactionList = transactionRepository.findByUserId(user.getId());
    transactionList.forEach(
        transaction -> {
          if (INCOME.equals(transaction.getType())) {
            balance.addAndGet(transaction.getAmount());
          } else {
            balance.addAndGet(-transaction.getAmount());
          }
        });
    return new app.cashquest.api.endpoint.rest.model.User()
        .balance(balance.get())
        .income(
            new Income()
                .amount(income.getAmount())
                .earningFrequency(income.getEarningFrequency())
                .savingTarget(income.getSavingTarget()))
        .user(toRest(user))
        .level(checkLevel())
        .profile(new Profile()
                .firstName(user.getFirstName())
                .sex(user.getSex())
                .lastName(user.getLastName()));
  }
}
