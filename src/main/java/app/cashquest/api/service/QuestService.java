package app.cashquest.api.service;

import app.cashquest.api.repository.QuestRepository;
import app.cashquest.api.repository.model.Quest;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestService {
  private final QuestRepository repository;

  public Quest getQuest(Integer actualLevel) {
    Optional<Quest> quest = repository.findByRequiredLevel(actualLevel);
    if(quest.isPresent()) {
      return quest.get();
    }
    return null;
  }
}
