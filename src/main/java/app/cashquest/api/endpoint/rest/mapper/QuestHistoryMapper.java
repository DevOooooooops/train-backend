package app.cashquest.api.endpoint.rest.mapper;

import app.cashquest.api.endpoint.rest.controller.mapper.QuestMapper;
import app.cashquest.api.endpoint.rest.model.CreateQuestHistory;
import app.cashquest.api.endpoint.rest.model.QuestHistory;
import app.cashquest.api.repository.model.User;
import app.cashquest.api.service.QuestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuestHistoryMapper {
  private final UserMapper userMapper;
  private final QuestService questService;
  private final QuestMapper questMapper;

  public QuestHistory toRest(app.cashquest.api.repository.model.QuestHistory domain, User user) {
    return new QuestHistory()
        .id(domain.getId())
        .status(domain.getStatus())
        .user(userMapper.domainToRest(user))
        .quest(questMapper.toRest(questService.getById(domain.getQuestId())));
  }

  public app.cashquest.api.repository.model.QuestHistory toDomain(CreateQuestHistory rest) {
    return app.cashquest.api.repository.model.QuestHistory.builder()
        .id(rest.getId())
        .userId(rest.getUserId())
        .questId(rest.getQuestId())
        .status(rest.getStatus())
        .build();
  }
}
