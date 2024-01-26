package app.cashquest.api.repository.model;

import app.cashquest.api.endpoint.rest.model.BudgetSavingMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import static org.hibernate.type.SqlTypes.JSON;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"user_table\"")
@Getter
@Setter
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private String id;
  private String username;
  private String password;
  private LocalDate birthdate;
  @JdbcTypeCode(value = JSON)
  private Income income;

}
