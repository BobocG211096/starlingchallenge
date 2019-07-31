package uk.starling.techchallenge.constraint;

import uk.starling.techchallenge.validator.TransactionTimetampsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TransactionTimetampsValidator.class  )
@Target ({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoundUpRequestTransactionTimestampConstraint {
    String message() default "The minimum timestamp value should not be" +
                             " bigger than the maximum timestamp value.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
