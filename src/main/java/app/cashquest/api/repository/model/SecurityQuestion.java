package app.cashquest.api.repository.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
@Getter
@Service
public class SecurityQuestion {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
    private String question;
}
