# Starling Challenge

Created an application which runs an embedded Tomcat server already configured by the spring framework starting on the port 8080.
It has a REST endpoint(/transacation/roundUp/) which can be invoked by a PUT request which will contain a body. Example of request body: 
``` json
{
 "savingGoalsName": "Saving Goal Challenge",	
 "minTransactionTimestamp": "2014-10-05T15:23:01Z",
 "maxTransactionTimestamp": "2019-10-20T15:23:01Z"
}
```
In return the application will send a response containing a list of all the rounded up accounts of the customer. Example of response:
``` json
{
    "roundUpResponses": [
        {
            "savingsGoalUid": "b7ee50b5-fb81-497a-8ff4-e326c453a272",
            "success": true,
            "errors": [],
            "accountId": "ab01e12e-acf9-4a9f-9d25-7ca448c2249c",
            "roundUpAmount": 32.33
        }
    ]
}
```
Where the ```accountId``` is the account of the customer which all transactions were rounded up and also the ```roundUpAmount``` which is the total of amount rounded up from each transaction.
