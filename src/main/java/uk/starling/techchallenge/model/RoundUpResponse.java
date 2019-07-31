package uk.starling.techchallenge.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class RoundUpResponse extends StarlingRoundUpResponse {
    //The specific account id against which a round up operation was performed.
    @NotNull
    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
