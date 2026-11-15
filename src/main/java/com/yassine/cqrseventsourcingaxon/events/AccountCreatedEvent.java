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
public class AccountCreatedEvent {

    private String accountId;
    private double initialBalance;
    private AccountStatus status;
    private String currency;

    @JsonCreator
    public AccountCreatedEvent(
            @JsonProperty("accountId") String accountId,
            @JsonProperty("initialBalance") double initialBalance,
            @JsonProperty("status") AccountStatus status,
            @JsonProperty("currency") String currency) {
        this.accountId = accountId;
        this.initialBalance = initialBalance;
        this.status = status;
        this.currency = currency;
    }
}