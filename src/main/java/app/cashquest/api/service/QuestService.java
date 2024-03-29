package app.cashquest.api.service;

import app.cashquest.api.endpoint.rest.security.exception.NotFoundException;
import app.cashquest.api.repository.QuestRepository;
import app.cashquest.api.repository.model.Quest;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestService {
  private final QuestRepository repository;

  public Quest getById(String id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Quest.id= " + id + " not found"));
  }

  public Quest getQuest(Integer actualLevel) {
    Optional<Quest> quest = repository.findByRequiredLevel(actualLevel);
    return quest.orElse(null);
  }
}
