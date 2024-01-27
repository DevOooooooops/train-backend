package app.cashquest.api.endpoint.rest.controller;

import app.cashquest.api.endpoint.rest.controller.mapper.QuestMapper;
import app.cashquest.api.endpoint.rest.model.Quest;
import app.cashquest.api.service.QuestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QuestController {
  private final QuestService service;
  private final QuestMapper mapper;

  @GetMapping("/quest")
  public Quest getQuest(@RequestParam Integer actualLevel) {
    return mapper.toRest(service.getQuest(actualLevel));
  }
}
