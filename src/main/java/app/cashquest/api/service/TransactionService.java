package app.cashquest.api.service;

import app.cashquest.api.endpoint.rest.mapper.UserMapper;
import app.cashquest.api.endpoint.rest.security.exception.NotFoundException;
import app.cashquest.api.repository.DAO.TransactionDAO;
import app.cashquest.api.repository.TransactionRepository;
import app.cashquest.api.repository.model.Transaction;
import java.time.LocalDate;
import app.cashquest.api.repository.model.User;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static app.cashquest.api.endpoint.rest.model.TransactionType.OUTCOME;

@Service
@AllArgsConstructor
public class TransactionService {
  private final TransactionRepository repository;
  private final TransactionDAO transactionDAO;
  private final UserService userService;
  private final UserMapper userMapper;

  public Transaction getBy(String id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Transaction.id = " + id + " not found."));
  }

  public List<Transaction> getAll() {
    return repository.findAll();
  }

  public Transaction save(Transaction transaction) {
    User user = userService.getUserById(transaction.getUserId());
    int balance = user.getBalance();
      if (transaction.getAmount() <= 0) {
        throw new IllegalArgumentException("Transaction amount must be positive.");
      }
      if (OUTCOME.equals(transaction.getType()) && transaction.getAmount() > balance) {
        throw new IllegalArgumentException("Insufficient balance for outcome transaction.");
      }
      userMapper.computeIncome(user);
      repository.save(transaction);
      return transaction;
  }

  public List<Transaction> getUserTransactionsFilteredByDate(
          User user,
          LocalDate startingDate, LocalDate endingDate) {
    return transactionDAO.findByStartingDateAndEndingDate(user.getId(), startingDate, endingDate);
  }

}
