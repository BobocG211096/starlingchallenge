package uk.starling.techchallenge.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RoundUpResponseForEachAccount {

    //List of round up responses for each account of a customer.
    @NotNull
    List<RoundUpResponse> roundUpResponses;

    public List<RoundUpResponse> getRoundUpResponses() {
        return roundUpResponses;
    }

    public void setRoundUpResponses(List<RoundUpResponse> roundUpResponses) {
        this.roundUpResponses = roundUpResponses;
    }
}
