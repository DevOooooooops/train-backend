package app.cashquest.api.service;

import app.cashquest.api.repository.UserRepository;
import app.cashquest.api.repository.model.User;
import app.cashquest.api.endpoint.rest.security.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User.username= " + username + " not found"));
    }
}