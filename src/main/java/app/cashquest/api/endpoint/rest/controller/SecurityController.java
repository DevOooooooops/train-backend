package app.cashquest.api.endpoint.rest.controller;

import app.cashquest.api.endpoint.rest.controller.mapper.SecurityQuestionMapper;
import app.cashquest.api.endpoint.rest.model.SecurityQuestion;
import app.cashquest.api.service.SecurityQuestionService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SecurityController {
    private final SecurityQuestionService service;
    private final SecurityQuestionMapper mapper;

    @GetMapping("/security_questions")
    public List<SecurityQuestion> getSecurityQuestions() {
        return service.findAll().stream().map(mapper::toRest).toList();
    }
}
