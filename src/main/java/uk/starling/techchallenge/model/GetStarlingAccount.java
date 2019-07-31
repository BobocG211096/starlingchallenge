package uk.starling.techchallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetStarlingAccount {
    @JsonProperty("accounts")
    private List<StarlingAccount> accounts;

    public List<StarlingAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<StarlingAccount> starlingAccoutList) {
        this.accounts = starlingAccoutList;
    }
}
