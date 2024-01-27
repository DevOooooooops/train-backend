package app.cashquest.api.repository.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
public class SecurityQuestionAnswer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
    private String questionId;
    private String answer;
    private Instant answeredAt;
}
