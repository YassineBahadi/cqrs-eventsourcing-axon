package com.yassine.cqrseventsourcingaxon.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yassine.cqrseventsourcingaxon.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author pc
 **/

@Getter
public class AccountCreditedEvent {

    private String accountId;
    private double amount;
    private String currency;

    @JsonCreator
    public AccountCreditedEvent(
            @JsonProperty("accountId") String accountId,
            @JsonProperty("amount") double amount,
            @JsonProperty("currency") String currency) {
        this.accountId = accountId;
        this.amount = amount;
        this.currency = currency;
    }
}