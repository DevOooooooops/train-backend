package app.cashquest.api.repository;

import app.cashquest.api.repository.model.SecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, String> {
}
