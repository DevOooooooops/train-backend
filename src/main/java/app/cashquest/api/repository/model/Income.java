package app.cashquest.api.repository.model;

import app.cashquest.api.endpoint.rest.model.BudgetSavingMode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Income {
  private int amount;
  private BudgetSavingMode earningFrequency;
  private int savingTarget;
}
