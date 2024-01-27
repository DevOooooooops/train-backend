package app.cashquest.api.endpoint.rest.controller.mapper;

import app.cashquest.api.repository.model.SecurityQuestionAnswer;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityQuestionAnswerMapper {
    private final PasswordEncoder passwordEncoder;

    public SecurityQuestionAnswer toDomain(String questionId, String answer) {
        return SecurityQuestionAnswer.builder()
                .answer(passwordEncoder.encode(answer))
                .questionId(questionId)
                .build();
    }
}
