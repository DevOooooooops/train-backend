package app.cashquest.api.endpoint.rest.mapper;

import app.cashquest.api.endpoint.rest.model.Income;
import org.springframework.stereotype.Component;

@Component
public class IncomeMapper {
  public app.cashquest.api.repository.model.Income toDomain(Income rest) {
    return app.cashquest.api.repository.model.Income.builder()
        .amount(rest.getAmount() != null ? rest.getAmount() : 0)
        .earningFrequency(rest.getEarningFrequency())
        .build();
  }
}
