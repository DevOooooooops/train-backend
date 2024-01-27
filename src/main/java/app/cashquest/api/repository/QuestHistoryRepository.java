package app.cashquest.api.repository;

import app.cashquest.api.repository.model.QuestHistory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestHistoryRepository extends JpaRepository<QuestHistory, String> {
  List<QuestHistory> findAllByUserId(String userId);

  Optional<QuestHistory> findByQuestIdAndAndUserId(String questId, String userId);
}
