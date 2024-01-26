package app.cashquest.api.endpoint.rest.controller;


import app.cashquest.api.endpoint.rest.mapper.UserMapper;
import app.cashquest.api.endpoint.rest.model.CreateUser;
import app.cashquest.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
  private UserService userService;
  private UserMapper userMapper;

  @PostMapping("/user")
  private CreateUser createUser(@RequestBody CreateUser user){
     return userMapper.toRest(userService.crupdateUser(userMapper.toDomain(user)));
  }

}
