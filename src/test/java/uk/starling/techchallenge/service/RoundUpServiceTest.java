package uk.starling.techchallenge.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import uk.starling.techchallenge.RoundUpTransactionBetweenRequest;
import uk.starling.techchallenge.client.StarlingClient;
import uk.starling.techchallenge.client.StarlingClient.HttpMethod;
import uk.starling.techchallenge.model.*;
import uk.starling.techchallenge.service.RoundUpService;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
public class RoundUpServiceTest {
    @Mock
    private StarlingClient starlingClient;

    @InjectMocks
    private RoundUpService roundUpService;

    @Test
    public void testRoundUp_happy() throws IOException, URISyntaxException {
        GetStarlingAccount getStarlingAccount = createGetStarlingAccount();
        GetTransaction getTransaction = createGetTransaction();
        StarlingRoundUpResponse starlingRoundUpResponse = createStarlingRoundUpResponse();

        RoundUpTransactionBetweenRequest roundUpTransactionBetweenRequest = new RoundUpTransactionBetweenRequest();
        roundUpTransactionBetweenRequest.setMinTransactionTimestamp("some-min-transaction-timestamp");
        roundUpTransactionBetweenRequest.setMaxTransactionTimestamp("some-max-transaction-timestamp");
        roundUpTransactionBetweenRequest.setSavingGoalsName("some-saving-goals-challenge");

        Mockito.when(starlingClient.sendRequest(any(URL.class), eq(HttpMethod.GET), isNull(), eq(GetStarlingAccount.class))).thenReturn(getStarlingAccount);
        Mockito.when(starlingClient.sendRequest(any(URL.class), eq(HttpMethod.GET), isNull(), eq(GetTransaction.class))).thenReturn(getTransaction);
        Mockito.when(starlingClient.sendRequest(any(URL.class), eq(HttpMethod.PUT), any(StarlingRoundUpRequest.class), eq(StarlingRoundUpResponse.class))).thenReturn(starlingRoundUpResponse);

        RoundUpResponseForEachAccount roundUpResponseForEachAccount = roundUpService.roundUp(roundUpTransactionBetweenRequest, "some-access-token");

        assertNotNull(roundUpResponseForEachAccount);
        assertEquals(1, roundUpResponseForEachAccount.getRoundUpResponses().size());
    }

    private GetStarlingAccount createGetStarlingAccount() {
        GetStarlingAccount accounts = new GetStarlingAccount();
        List<StarlingAccount> accountList = new ArrayList<>();

        StarlingAccount starlingAccount = createStarlingAccount();
        accountList.add(starlingAccount);

        accounts.setAccounts(accountList);

        return accounts;
    }

    private StarlingAccount createStarlingAccount() {
        StarlingAccount account = new StarlingAccount();

        account.setAccountUid("some-account-id");
        account.setCreatedAt("some-timestamp");
        account.setCurrency("some-currency");
        account.setDefaultCategory("some-default-category");

        return account;
    }

    private GetTransaction createGetTransaction() {
        GetTransaction getTransaction = new GetTransaction();
        List<FeedItem> feedItemList = new ArrayList<>();

        FeedItem feedItem = createFeedItem();
        feedItemList.add(feedItem);

        getTransaction.setFeedItems(feedItemList);

        return getTransaction;
    }

    private FeedItem createFeedItem() {
        FeedItem feedItem = new FeedItem();

        Amount amount = new Amount();
        amount.setMinorUnits(new BigDecimal(112));
        amount.setCurrency("GBP");

        feedItem.setAmount(amount);

        return feedItem;
    }

    private StarlingRoundUpResponse createStarlingRoundUpResponse(){
        StarlingRoundUpResponse starlingRoundUpResponse = new StarlingRoundUpResponse();

        starlingRoundUpResponse.setSavingsGoalUid("some-savingsGoualUid-id");
        starlingRoundUpResponse.setSuccess(true);
        starlingRoundUpResponse.setErrors(emptyList());

        return starlingRoundUpResponse;
    }
}
