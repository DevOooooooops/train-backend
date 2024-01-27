package app.cashquest.api.repository;

import app.cashquest.api.repository.model.Quest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends JpaRepository<Quest, String> {
  Optional<Quest> findByRequiredLevel(Integer actualLevel);
}
