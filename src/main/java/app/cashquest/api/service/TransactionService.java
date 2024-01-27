package app.cashquest.api.service;

import static java.time.ZoneOffset.UTC;

import app.cashquest.api.endpoint.rest.security.exception.NotFoundException;
import app.cashquest.api.repository.DAO.TransactionDAO;
import app.cashquest.api.repository.TransactionRepository;
import app.cashquest.api.repository.model.Transaction;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
  private final TransactionRepository repository;
  private final TransactionDAO transactionDAO;

  public Transaction getBy(String id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Transaction.id = " + id + " not found."));
  }

  public List<Transaction> getAll() {
    return repository.findAll();
  }

    public Transaction save(Transaction transaction) {
        //TODO: how does income outcome work ?
        return repository.save(transaction);
    }

    public List<Transaction> transactionsFilteredByDate(LocalDateTime startingDate, LocalDateTime endingDate){
        return transactionDAO.findByStartingDateAndEndingDate(startingDate.toInstant(UTC), endingDate.toInstant(UTC));
    }
}
