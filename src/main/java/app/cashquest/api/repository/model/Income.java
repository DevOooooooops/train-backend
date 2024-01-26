package app.cashquest.api.repository.model;


import app.cashquest.api.endpoint.rest.model.BudgetSavingMode;
import lombok.Data;

@Data
public class Income {
  private int amount;
  private BudgetSavingMode earningFrequency;
}
