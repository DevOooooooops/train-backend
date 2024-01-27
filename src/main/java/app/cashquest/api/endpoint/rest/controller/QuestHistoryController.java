package app.cashquest.api.endpoint.rest.controller;

import app.cashquest.api.endpoint.rest.model.QuestHistory;
import app.cashquest.api.service.QuestHistoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QuestHistoryController {
  private QuestHistoryService service;

  @GetMapping("/user/{userId}/quest/history")
  public List<QuestHistory> getHistories(@PathVariable String userId) {
    return service.getQuestHistoriesByUser(userId);
  }
}
