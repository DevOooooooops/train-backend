package app.cashquest.api.repository.DAO;

import static java.time.LocalTime.MAX;
import static java.time.ZoneOffset.UTC;

import app.cashquest.api.repository.model.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class TransactionDAO {
  private EntityManager entityManager;

  public List<Transaction> findByStartingDateAndEndingDate(
          LocalDate startingDate, LocalDate endingDate) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
    Root<Transaction> root = query.from(Transaction.class);

    List<Predicate> predicates = new ArrayList<>();

    if (startingDate != null) {
      Predicate startingDatePredicate =
          cb.greaterThanOrEqualTo(root.get("creationDatetime"), startingDate.atStartOfDay().toInstant(UTC));
      predicates.add(startingDatePredicate);
    }

    if (endingDate != null) {
      Predicate endingDatePredicate =
          cb.lessThanOrEqualTo(root.get("creationDatetime"), endingDate.atTime(MAX).toInstant(UTC));
      predicates.add(endingDatePredicate);
    }

    query.where(predicates.toArray(Predicate[]::new));

    return entityManager.createQuery(query).getResultList();
  }
}
