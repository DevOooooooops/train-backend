package app.cashquest.api.repository;

import app.cashquest.api.repository.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
  List<Transaction> findByUserId(String userId);
}
