package app.cashquest.api.endpoint.rest.controller.validator;

import app.cashquest.api.endpoint.rest.model.Income;
import app.cashquest.api.endpoint.rest.security.exception.BadRequestException;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

@Component
public class IncomeValidator implements Consumer<Income> {
    @Override
    public void accept(Income income) {
        StringBuilder stringBuilder = new StringBuilder();
        if (income.getAmount() == null) {
            stringBuilder.append("amount is mandatory");
        }
        if (income.getAmount() < 0) {
            stringBuilder.append("amount must be > 0");
        }
        if (income.getSavingTarget() == null) {
            stringBuilder.append("saving target is mandatory");
        }
        if (income.getSavingTarget() >= 100 || income.getSavingTarget() <= 0){
            stringBuilder.append("savingTarget must be between 1 and 99");
        }
        if (!stringBuilder.isEmpty()) {
            throw new BadRequestException(stringBuilder.toString());
        }
    }
}
