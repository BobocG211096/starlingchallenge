package uk.starling.techchallenge.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RoundUpResponse extends StarlingRoundUpResponse {
    //The specific account id against which a round up operation was performed.
    @NotNull
    private String accountId;

    //The round up amount saved in the saving goal
    @NotNull
    private BigDecimal roundUpAmount;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getRoundUpAmount() {
        return roundUpAmount;
    }

    public void setRoundUpAmount(BigDecimal roundUpAmount) {
        this.roundUpAmount = roundUpAmount;
    }
}
