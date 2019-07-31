package uk.starling.techchallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.starling.techchallenge.RoundUpTransactionBetweenRequest;
import uk.starling.techchallenge.client.StarlingClient;
import uk.starling.techchallenge.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static uk.starling.techchallenge.client.StarlingClient.HttpMethod.GET;
import static uk.starling.techchallenge.client.StarlingClient.HttpMethod.PUT;

@Component
public class RoundUpService {
    private StarlingClient starlingClient;
    private final String baseUrl = "https://api-sandbox.starlingbank.com/api/v2";

    @Autowired
    public RoundUpService(StarlingClient starlingClient) {
        this.starlingClient = starlingClient;
    }

    public RoundUpResponseForEachAccount roundUp(RoundUpTransactionBetweenRequest request, String accessToken) throws IOException, URISyntaxException {
        RoundUpResponseForEachAccount roundUpResponseForEachAccount = new RoundUpResponseForEachAccount();
        List<RoundUpResponse> roundUpResponses = new ArrayList<>();
        URL url = new URL(baseUrl + "/accounts");

        starlingClient.setAccesToken(accessToken);
        String currency = null;

        GetStarlingAccount starlingAccounts = starlingClient.sendRequest(url, GET, null, GetStarlingAccount.class);
        StarlingRoundUpResponse starlingRoundUpResponse = null;

        for (StarlingAccount account : starlingAccounts.getAccounts()) {
            BigDecimal roundUpAmount = BigDecimal.ZERO;
            RoundUpResponse roundUpResponse = new RoundUpResponse();
            GetTransaction transactions = getTransactions(request, account);

            for (FeedItem feedItem : transactions.getFeedItems()) {
                BigDecimal minorUnits = feedItem.getAmount().getMinorUnits();
                BigDecimal decimalUnit = minorUnits.divide(new BigDecimal(100));

                roundUpAmount = roundUpTheAmount(roundUpAmount, decimalUnit);
                currency = feedItem.getAmount().getCurrency();
            }

            StarlingRoundUpRequest starlingRoundUpRequest = buildStarlingRoundUpRequest(request, currency, roundUpAmount);

            url = new URL(baseUrl + "/account/" + account.getAccountUid() + "/savings-goals");
            starlingRoundUpResponse = starlingClient.sendRequest(url, PUT, starlingRoundUpRequest, StarlingRoundUpResponse.class);

            setRoundUpResponseList(starlingRoundUpResponse, account, roundUpResponse, roundUpResponses, roundUpAmount);
            roundUpResponseForEachAccount.setRoundUpResponses(roundUpResponses);
        }

        return roundUpResponseForEachAccount;
    }

    private BigDecimal roundUpTheAmount(BigDecimal roundUpAmount, BigDecimal decimalUnit) {
        roundUpAmount = roundUpAmount.add((decimalUnit.setScale(0, RoundingMode.CEILING).subtract(decimalUnit)));
        return roundUpAmount;
    }

    private void setRoundUpResponseList(StarlingRoundUpResponse starlingRoundUpResponse, StarlingAccount account, RoundUpResponse roundUpResponse, List<RoundUpResponse> roundUpResponses, BigDecimal roundUpAmount) {
        roundUpResponse.setSavingsGoalUid(starlingRoundUpResponse.getSavingsGoalUid());
        roundUpResponse.setErrors(starlingRoundUpResponse.getErrors());
        roundUpResponse.setSuccess(starlingRoundUpResponse.isSuccess());
        roundUpResponse.setErrors(starlingRoundUpResponse.getErrors());
        roundUpResponse.setAccountId(account.getAccountUid());
        roundUpResponse.setRoundUpAmount(roundUpAmount);

        roundUpResponses.add(roundUpResponse);
    }

    private GetTransaction getTransactions(RoundUpTransactionBetweenRequest request, StarlingAccount account) throws IOException, URISyntaxException {
        String queryMinTransactionTimestamp = String.format("minTransactionTimestamp=%s",
                URLEncoder.encode(request.getMinTransactionTimestamp(), "UTF-8"));
        String queryMaxTransactionTimestamp = String.format("maxTransactionTimestamp=%s",
                URLEncoder.encode(request.getMaxTransactionTimestamp(), "UTF-8"));

        URL url = new URL(baseUrl + "/feed/account/" + account.getAccountUid() +
                "/category/" + account.getDefaultCategory() + "/transactions-between" +
                "?" + queryMinTransactionTimestamp +
                "&" + queryMaxTransactionTimestamp);

        return starlingClient.sendRequest(url, GET, null, GetTransaction.class);
    }

    private StarlingRoundUpRequest buildStarlingRoundUpRequest(RoundUpTransactionBetweenRequest request, String currency, BigDecimal roundUpAmount) {
        StarlingRoundUpRequest starlingRoundUpRequest = new StarlingRoundUpRequest();
        starlingRoundUpRequest.setName(request.getSavingGoalsName());
        starlingRoundUpRequest.setCurrency(currency);
        BigDecimal minorUnits = roundUpAmount.multiply(new BigDecimal(100));
        Target target = new Target();
        target.setMinorUnits(minorUnits);
        target.setCurrency(currency);
        starlingRoundUpRequest.setTarget(target);
        return starlingRoundUpRequest;
    }

}
