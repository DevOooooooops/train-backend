package app.cashquest.api.service;

import app.cashquest.api.endpoint.rest.model.CreateQuestHistory;
import app.cashquest.api.repository.model.Quest;
import app.cashquest.api.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScoreService {
  private final QuestService questService;
  private final UserService userService;
  public void updateScore(CreateQuestHistory history) {
    User actualUser = userService.getUserById(history.getUserId());
    Quest actualQuest = questService.getById(history.getQuestId());
    actualUser.setScore(actualUser.getScore() + actualQuest.getPoints());
    if (actualUser.getScore() >= actualUser.getLevel() * 5) {
      actualUser.setLevel(actualUser.getLevel() + 1);
    }
    userService.crupdateUser(actualUser);
  }
}
