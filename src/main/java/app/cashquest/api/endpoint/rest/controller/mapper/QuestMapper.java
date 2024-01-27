package app.cashquest.api.endpoint.rest.controller.mapper;

import app.cashquest.api.endpoint.rest.model.Quest;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class QuestMapper {
  public Quest toRest(app.cashquest.api.repository.model.Quest domain) {
    return new Quest()
        .id(domain.getId())
        .name(domain.getName())
        .objectiveDescription(domain.getObjectiveDescription())
        .amountObjective(BigDecimal.valueOf(domain.getAmountObjective()))
        .points(BigDecimal.valueOf(domain.getPoints()))
        .requiredLevel(BigDecimal.valueOf(domain.getRequiredLevel()));
  }
}
