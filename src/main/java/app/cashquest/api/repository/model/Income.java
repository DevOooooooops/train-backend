package app.cashquest.api.repository.model;


import app.cashquest.api.endpoint.rest.model.BudgetSavingMode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class Income {
  private int amount;
  @Enumerated(EnumType.STRING)
  private BudgetSavingMode earningFrequency;
}
