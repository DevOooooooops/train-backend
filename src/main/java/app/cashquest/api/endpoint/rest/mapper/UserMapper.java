package app.cashquest.api.endpoint.rest.mapper;

import static app.cashquest.api.endpoint.rest.model.TransactionType.INCOME;

import app.cashquest.api.endpoint.rest.model.BudgetSavingMode;
import app.cashquest.api.endpoint.rest.model.CreateUser;
import app.cashquest.api.endpoint.rest.model.CreatedUser;
import app.cashquest.api.endpoint.rest.model.Income;
import app.cashquest.api.endpoint.rest.model.Profile;
import app.cashquest.api.endpoint.rest.model.UpdateUser;
import app.cashquest.api.endpoint.rest.security.model.Principal;
import app.cashquest.api.repository.TransactionRepository;
import app.cashquest.api.repository.UserRepository;
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
  private final UserRepository userRepository;

  public User toDomain(CreateUser user) {
    return User.builder()
        .username(user.getUsername())
        .password(passwordEncoder.encode(user.getPassword()))
        .birthdate(LocalDate.from(Objects.requireNonNull(user.getBirthDate())))
        .score(0)
        .balance(0)
        .build();
  }

  public CreatedUser toRest(User domain) {
    return new CreatedUser()
        .id(domain.getId())
        .username(domain.getUsername())
        .birthDate(LocalDate.from(domain.getBirthdate()));
  }

  public User toDomain(UpdateUser user, Principal principal) {
    CreatedUser userPayload = user.getUser();
    User actual = principal.getUser();
    Profile profile = user.getProfile();
    return User.builder()
        .id(actual.getId())
        .password(principal.getPassword())
        .username(Objects.requireNonNull(userPayload).getUsername())
        .birthdate(LocalDate.from(Objects.requireNonNull(userPayload.getBirthDate())))
        .income(incomeMapper.toDomain(Objects.requireNonNull(user.getIncome())))
        .firstName(profile == null ? null : profile.getFirstName())
        .lastName(profile == null ? null : profile.getFirstName())
        .sex(profile == null ? null : profile.getSex())
        .balance(actual.getBalance())
        .score(actual.getScore())
        .level(actual.getLevel())
        .build();
  }

  public User computeIncome(User user){
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
    user.setBalance(balance.get());
    return userRepository.save(user);
  }

  public app.cashquest.api.endpoint.rest.model.User domainToRest(User user) {
    app.cashquest.api.repository.model.Income income = user.getIncome();
    User actual = computeIncome(user);
    return new app.cashquest.api.endpoint.rest.model.User()
        .balance(actual.getBalance())
        .level(user.getLevel())
        .score(user.getScore())
        .income(income != null ?
            new Income()
                .amount(income.getAmount())
                .earningFrequency(income.getEarningFrequency())
                .savingTarget(income.getSavingTarget()) : new Income().earningFrequency(BudgetSavingMode.DAILY))
        .user(toRest(user))
        .profile(
            new Profile()
                .firstName(user.getFirstName())
                .sex(user.getSex())
                .lastName(user.getLastName()))
        .user(toRest(user));
  }
}
