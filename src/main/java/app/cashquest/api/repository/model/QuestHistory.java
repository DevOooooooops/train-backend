package app.cashquest.api.repository.model;

import static jakarta.persistence.EnumType.STRING;

import app.cashquest.api.endpoint.rest.model.QuestStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name = "\"quest_history\"")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String userId;
  private String questId;

  @Enumerated(STRING)
  @ColumnTransformer(write = "?::quest_status")
  private QuestStatus status;
}
