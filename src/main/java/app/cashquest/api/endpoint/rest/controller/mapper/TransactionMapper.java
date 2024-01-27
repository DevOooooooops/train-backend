package app.cashquest.api.endpoint.rest.controller.mapper;

import app.cashquest.api.endpoint.rest.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
  public Transaction toRest(app.cashquest.api.repository.model.Transaction domain) {
    return new Transaction()
        .id(domain.getId())
        .amount(domain.getAmount())
        .reason(domain.getReason())
        .creationDatetime(domain.getCreationDatetime())
        .type(domain.getType());
  }

  public app.cashquest.api.repository.model.Transaction toDomain(Transaction rest, String userId) {
    return app.cashquest.api.repository.model.Transaction.builder()
        .id(rest.getId())
        .amount(rest.getAmount())
        .type(rest.getType())
        .userId(userId)
        .reason(rest.getReason())
        .build();
  }
}
