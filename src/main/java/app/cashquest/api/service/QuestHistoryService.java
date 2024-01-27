package app.cashquest.api.service;

import app.cashquest.api.endpoint.rest.mapper.QuestHistoryMapper;
import app.cashquest.api.endpoint.rest.model.QuestHistory;
import app.cashquest.api.repository.QuestHistoryRepository;
import app.cashquest.api.repository.model.User;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestHistoryService {
  private final QuestHistoryRepository repository;
  private final UserService userService;
  private final QuestHistoryMapper mapper;

  public List<QuestHistory> getQuestHistoriesByUser(String userId) {
    List<app.cashquest.api.repository.model.QuestHistory> actual = repository.findAllByUserId(userId);
    User user = userService.getUserById(userId);
    return actual.stream()
        .map(questHistory -> mapper.toRest(questHistory, user))
        .toList();
  }

}
