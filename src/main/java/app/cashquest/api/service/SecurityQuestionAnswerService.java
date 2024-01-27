package app.cashquest.api.service;

import app.cashquest.api.repository.SecurityQuestionAnswerRepository;
import app.cashquest.api.repository.SecurityQuestionRepository;
import app.cashquest.api.repository.model.SecurityQuestion;
import app.cashquest.api.repository.model.SecurityQuestionAnswer;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityQuestionAnswerService {
    private final SecurityQuestionAnswerRepository securityQuestionRepository;
    public SecurityQuestionAnswer answer(SecurityQuestionAnswer answer){
        return securityQuestionRepository.save(answer);
    }
}
