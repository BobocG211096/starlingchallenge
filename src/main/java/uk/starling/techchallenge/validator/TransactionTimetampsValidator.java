package uk.starling.techchallenge.validator;


import uk.starling.techchallenge.RoundUpTransactionBetweenRequest;
import uk.starling.techchallenge.constraint.RoundUpRequestTransactionTimestampConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;

public class TransactionTimetampsValidator implements ConstraintValidator<RoundUpRequestTransactionTimestampConstraint, RoundUpTransactionBetweenRequest> {
    @Override
    public boolean isValid(RoundUpTransactionBetweenRequest request, ConstraintValidatorContext constraintValidatorContext) {
         compareTimestampsFromRoundUpRequest(request);

        return true;

    }

    private void compareTimestampsFromRoundUpRequest(RoundUpTransactionBetweenRequest request) {
        Instant minTransactionTimestamp = Instant.parse(request.getMinTransactionTimestamp());
        Instant maxTransactionTimestamp = Instant.parse(request.getMaxTransactionTimestamp());

        if(minTransactionTimestamp.compareTo(maxTransactionTimestamp) == 1){
            throw new RuntimeException("The minTransactionTimestamp " + minTransactionTimestamp +" should not be later than" +
                    "the maxTransactionTimestamp " + maxTransactionTimestamp);
        }


    }
}

