package app.cashquest.api.endpoint.rest.mapper;

import app.cashquest.api.endpoint.rest.controller.validator.IncomeValidator;
import app.cashquest.api.endpoint.rest.model.Income;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IncomeMapper {
  private final IncomeValidator validator;

  public app.cashquest.api.repository.model.Income toDomain(Income rest) {
    validator.accept(rest);
    return app.cashquest.api.repository.model.Income.builder()
        .amount(rest.getAmount() != null ? rest.getAmount() : 0)
        .earningFrequency(rest.getEarningFrequency())
        .savingTarget(rest.getSavingTarget() != null ? rest.getSavingTarget() : 0)
        .build();
  }
}
