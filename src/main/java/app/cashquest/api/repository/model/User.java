package app.cashquest.api.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"user\"")
@Getter
@Setter
public class User {
  @Id
  @Column(name = "id", nullable = false)
  private String id;

  private String username;
  private String password;
  private LocalDate birthdate;
}
