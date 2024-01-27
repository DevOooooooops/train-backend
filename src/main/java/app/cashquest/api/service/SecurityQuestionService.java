package app.cashquest.api.service;

import app.cashquest.api.repository.SecurityQuestionAnswerRepository;
import app.cashquest.api.repository.SecurityQuestionRepository;
import app.cashquest.api.repository.model.SecurityQuestion;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityQuestionService {
    private final SecurityQuestionRepository securityQuestionRepository;

    public List<SecurityQuestion> findAll() {
        return securityQuestionRepository.findAll();
    }
}
