package uk.starling.techchallenge;

import uk.starling.techchallenge.constraint.RoundUpRequestTransactionTimestampConstraint;

import javax.validation.constraints.NotNull;

@RoundUpRequestTransactionTimestampConstraint
public class RoundUpTransactionBetweenRequest {
    @NotNull
    private String savingGoalsName;

    @NotNull
    private String minTransactionTimestamp;

    @NotNull
    private String maxTransactionTimestamp;

    public String getSavingGoalsName() {
        return savingGoalsName;
    }

    public void setSavingGoalsName(String savingGoalsName) {
        this.savingGoalsName = savingGoalsName;
    }

    public String getMinTransactionTimestamp() {
        return minTransactionTimestamp;
    }

    public void setMinTransactionTimestamp(String minTransactionTimestamp) {
        this.minTransactionTimestamp = minTransactionTimestamp;
    }

    public String getMaxTransactionTimestamp() {
        return maxTransactionTimestamp;
    }

    public void setMaxTransactionTimestamp(String maxTransactionTimestamp) {
        this.maxTransactionTimestamp = maxTransactionTimestamp;
    }
}
