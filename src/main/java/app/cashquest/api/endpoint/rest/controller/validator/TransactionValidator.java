package app.cashquest.api.endpoint.rest.controller.validator;

import app.cashquest.api.endpoint.rest.model.Transaction;
import app.cashquest.api.endpoint.rest.security.exception.BadRequestException;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

@Component
public class TransactionValidator implements Consumer<Transaction> {
    @Override
    public void accept(Transaction transaction) {
        StringBuilder stringBuilder = new StringBuilder();
        if (transaction.getAmount() == null){
            stringBuilder.append("amount is mandatory");
        }
        if (transaction.getAmount() < 0){
            stringBuilder.append("amount must be > 0");
        }
        if (!stringBuilder.isEmpty()){
            throw new BadRequestException(stringBuilder.toString());
        }
    }
}
