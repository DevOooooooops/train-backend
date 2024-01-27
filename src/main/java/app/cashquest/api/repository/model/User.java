package app.cashquest.api.repository.model;

import static org.hibernate.type.SqlTypes.JSON;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.hibernate.annotations.JdbcTypeCode;

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

  @Column(name = "password", updatable = false)
  private String password;

  private LocalDate birthdate;

  @JdbcTypeCode(value = JSON)
  private Income income;
}
