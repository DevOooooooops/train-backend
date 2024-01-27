package app.cashquest.api.service;

import static app.cashquest.api.endpoint.rest.model.QuestStatus.IN_PROGRESS;
import static app.cashquest.api.endpoint.rest.model.QuestStatus.SUCCESS;

import app.cashquest.api.endpoint.rest.mapper.QuestHistoryMapper;
import app.cashquest.api.endpoint.rest.model.CreateQuestHistory;
import app.cashquest.api.endpoint.rest.model.QuestHistory;
import app.cashquest.api.endpoint.rest.security.model.Principal;
import app.cashquest.api.repository.QuestHistoryRepository;
import app.cashquest.api.repository.model.Quest;
import app.cashquest.api.repository.model.User;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestHistoryService {
  private final QuestHistoryRepository repository;
  private final UserService userService;
  private final QuestHistoryMapper mapper;
  private final ScoreService scoreService;

  public List<QuestHistory> getQuestHistoriesByUser(String userId) {
    List<app.cashquest.api.repository.model.QuestHistory> actual =
        repository.findAllByUserId(userId);
    User user = userService.getUserById(userId);
    return actual.stream().map(questHistory -> mapper.toRest(questHistory, user)).toList();
  }

  public List<QuestHistory> crupdateUserQuestStatus(
      List<CreateQuestHistory> toSave, Principal principal) {
    List<app.cashquest.api.repository.model.QuestHistory> saved =
        repository.saveAll(
            toSave.stream().map(this::checkExistence).map(mapper::toDomain).toList());
    return saved.stream()
        .map(questHistory -> mapper.toRest(questHistory, principal.getUser()))
        .toList();
  }

  private CreateQuestHistory checkExistence(CreateQuestHistory history) {
    Optional<app.cashquest.api.repository.model.QuestHistory> actual =
        repository.findByQuestIdAndAndUserId(history.getQuestId(), history.getUserId());
    if (actual.isPresent()) {
      history.setId(actual.get().getId());
      if (history.getStatus() != null
          && history.getStatus() == SUCCESS
          && actual.get().getStatus() == IN_PROGRESS) {
        scoreService.updateScore(history);
      }
      history.setStatus(
          history.getStatus() == null ? actual.get().getStatus() : history.getStatus());
    }
    return history;
  }
}
