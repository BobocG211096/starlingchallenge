package uk.starling.techchallenge.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import uk.starling.techchallenge.RoundUpTransactionBetweenRequest;
import uk.starling.techchallenge.model.RoundUpResponseForEachAccount;
import uk.starling.techchallenge.service.RoundUpService;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class RoundUpController {

    private final RoundUpService roundUpService;

    @Autowired
    public RoundUpController(RoundUpService roundUpService) {
        this.roundUpService = roundUpService;
    }

    //This endpoint is performing a round up for each account that a customer is holding between the a given two timestamps of the transactions.
    @PutMapping("/transacation/roundUp/")
    public RoundUpResponseForEachAccount roundUp(@RequestBody @Valid RoundUpTransactionBetweenRequest request,
                                                 @RequestHeader(value = "Authorization") String accessToken) throws IOException, URISyntaxException {
        return roundUpService.roundUp(request, accessToken);
    }
}
