package app.cashquest.api.repository;

import app.cashquest.api.repository.model.SecurityQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityQuestionAnswerRepository extends JpaRepository<SecurityQuestionAnswer, String> {
}