package app.cashquest.api.endpoint.rest.controller;

import app.cashquest.api.endpoint.rest.controller.mapper.SecurityQuestionAnswerMapper;
import app.cashquest.api.endpoint.rest.mapper.UserMapper;
import app.cashquest.api.endpoint.rest.model.CreateUser;
import app.cashquest.api.endpoint.rest.model.CreatedUser;
import app.cashquest.api.endpoint.rest.model.UpdateUser;
import app.cashquest.api.endpoint.rest.model.User;
import app.cashquest.api.endpoint.rest.security.model.Principal;
import app.cashquest.api.service.SecurityQuestionAnswerService;
import app.cashquest.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
  private final UserService userService;
  private final UserMapper userMapper;
  private final SecurityQuestionAnswerService questionAnswerService;
  private final SecurityQuestionAnswerMapper securityQuestionAnswerMapper;

  @PostMapping("/user")
  private CreatedUser createUser(@RequestBody CreateUser user) {
    var saved = userService.crupdateUser(userMapper.toDomain(user));
    questionAnswerService.answer(securityQuestionAnswerMapper.toDomain(user.getSecurityQuestionId(), user.getSecurityQuestionAnswer()));
    return userMapper.toRest(saved);
  }

  @PutMapping("/user")
  private User crupdateUser(
      @RequestBody UpdateUser user, @AuthenticationPrincipal Principal principal) {
    return userMapper.domainToRest(userService.crupdateUser(userMapper.toDomain(user, principal)));
  }

  @GetMapping("/user")
  private User getCurrentUser(@AuthenticationPrincipal Principal principal) {
    return userMapper.domainToRest(userService.getUserById(principal.getUser().getId()));
  }
}
