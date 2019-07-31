package uk.starling.techchallenge.model;

public class FeedItem {
    private String feedItemUid;

    private String categoryUid;

    private Amount amount;

    private Amount sourceAmount;

    private String direction;

    private String updatedAt;

    private String transactionTime;

    private String settlementTime;

    private String source;

    private String sourceSubType;

    private String status;

    private String counterPartyType;

    private String counterPartyUid;

    private String counterPartyName;

    private String counterPartySubEntityName;

    private String counterPartySubEntityUid;

    private String counterPartySubEntityIdentifier;

    private String counterPartySubEntitySubIdentifier;

    private String reference;

    private String country;

    private String spendingCategory;

    public String getFeedItemUid() {
        return feedItemUid;
    }

    public void setFeedItemUid(String feedItemUid) {
        this.feedItemUid = feedItemUid;
    }

    public String getCategoryUid() {
        return categoryUid;
    }

    public void setCategoryUid(String categoryUid) {
        this.categoryUid = categoryUid;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Amount getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(Amount sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceSubType() {
        return sourceSubType;
    }

    public void setSourceSubType(String sourceSubType) {
        this.sourceSubType = sourceSubType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCounterPartyType() {
        return counterPartyType;
    }

    public void setCounterPartyType(String counterPartyType) {
        this.counterPartyType = counterPartyType;
    }

    public String getCounterPartySubEntityName() {
        return counterPartySubEntityName;
    }

    public void setCounterPartySubEntityName(String counterPartySubEntityName) {
        this.counterPartySubEntityName = counterPartySubEntityName;
    }

    public String getCounterPartySubEntityIdentifier() {
        return counterPartySubEntityIdentifier;
    }

    public void setCounterPartySubEntityIdentifier(String counterPartySubEntityIdentifier) {
        this.counterPartySubEntityIdentifier = counterPartySubEntityIdentifier;
    }

    public String getCounterPartySubEntitySubIdentifier() {
        return counterPartySubEntitySubIdentifier;
    }

    public void setCounterPartySubEntitySubIdentifier(String counterPartySubEntitySubIdentifier) {
        this.counterPartySubEntitySubIdentifier = counterPartySubEntitySubIdentifier;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSpendingCategory() {
        return spendingCategory;
    }

    public void setSpendingCategory(String spendingCategory) {
        this.spendingCategory = spendingCategory;
    }

    public String getCounterPartyUid() {
        return counterPartyUid;
    }

    public void setCounterPartyUid(String counterPartyUid) {
        this.counterPartyUid = counterPartyUid;
    }

    public String getCounterPartyName() {
        return counterPartyName;
    }

    public void setCounterPartyName(String counterPartyName) {
        this.counterPartyName = counterPartyName;
    }

    public String getCounterPartySubEntityUid() {
        return counterPartySubEntityUid;
    }

    public void setCounterPartySubEntityUid(String counterPartySubEntityUid) {
        this.counterPartySubEntityUid = counterPartySubEntityUid;
    }
}
