package app.cashquest.api.endpoint.rest.controller;

import app.cashquest.api.endpoint.rest.controller.mapper.TransactionMapper;
import app.cashquest.api.endpoint.rest.model.Transaction;
import app.cashquest.api.endpoint.rest.security.model.Principal;
import app.cashquest.api.service.TransactionService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TransactionController {
  private final TransactionService service;
  private final TransactionMapper mapper;
  @GetMapping("/user/transactions")
  public List<Transaction> getFilteredTransactions(
      @RequestParam(name = "starting_date")LocalDate startingDate,
      @RequestParam(name = "ending_date")LocalDate endingDate){
    return service.transactionsFilteredByDate(startingDate.atStartOfDay(), endingDate.atTime(LocalTime.MAX))
        .stream().map(mapper::toRest).toList();
  }

  @GetMapping("/user/transactions/{id}")
  public Transaction getTransaction(@PathVariable String id) {
    return mapper.toRest(service.getBy(id));
  }

  @PutMapping("/user/transactions/{id}")
  public Transaction crupdateTransaction(
      @PathVariable String id,
      @RequestBody Transaction payload,
      @AuthenticationPrincipal Principal principal) {
    return mapper.toRest(service.save(mapper.toDomain(payload, principal.getUser().getId())));
  }
}
