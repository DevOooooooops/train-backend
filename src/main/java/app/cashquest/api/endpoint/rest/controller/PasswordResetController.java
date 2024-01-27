package app.cashquest.api.endpoint.rest.controller;

import app.cashquest.api.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PasswordResetController {
    private final AuthenticationService authenticationService;

    @PostMapping("/reset_password_token")
    public String createResetPasswordToken()
}
