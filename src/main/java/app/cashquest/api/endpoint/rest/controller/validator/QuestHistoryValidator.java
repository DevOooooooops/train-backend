package app.cashquest.api.endpoint.rest.controller.validator;

import app.cashquest.api.endpoint.rest.model.CreateQuestHistory;
import app.cashquest.api.endpoint.rest.security.exception.BadRequestException;
import app.cashquest.api.repository.model.QuestHistory;
import app.cashquest.api.service.QuestHistoryService;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuestHistoryValidator implements Consumer<CreateQuestHistory> {
  private final QuestHistoryService service;

  @Override
  public void accept(CreateQuestHistory createQuestHistory) {
    StringBuilder stringBuilder = new StringBuilder();
    if(createQuestHistory.getQuestId() == null) {
      stringBuilder.append("questId is mandatory.");
    }
    if (createQuestHistory.getUserId() == null) {
      stringBuilder.append("userId is mandatory.");
    }
    if (!stringBuilder.isEmpty()) {
      throw new BadRequestException(stringBuilder.toString());
    }
  }
}
