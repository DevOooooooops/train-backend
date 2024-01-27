package app.cashquest.api.endpoint.rest.controller.mapper;

import app.cashquest.api.endpoint.rest.model.SecurityQuestion;
import org.springframework.stereotype.Component;

@Component
public class SecurityQuestionMapper {
    public SecurityQuestion toRest(app.cashquest.api.repository.model.SecurityQuestion domain){
        return new SecurityQuestion()
                .id(domain.getId())
                .question(domain.getQuestion());
    }
}
