package app.cashquest.api.repository.model;


import app.cashquest.api.endpoint.rest.model.BudgetSavingMode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

@Data
public class Income {
  private int amount;
  private BudgetSavingMode earningFrequency;
}
