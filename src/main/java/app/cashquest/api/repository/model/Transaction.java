package app.cashquest.api.repository.model;

import static jakarta.persistence.EnumType.STRING;

import app.cashquest.api.endpoint.rest.model.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"transaction\"")
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    private String id;
    private int amount;
    private String userId;
    @Enumerated(STRING)
    @ColumnTransformer(write = "?::transaction_type")
    private TransactionType type;
    private String reason;
}
