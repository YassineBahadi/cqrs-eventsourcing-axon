package com.yassine.cqrseventsourcingaxon.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yassine.cqrseventsourcingaxon.enums.AccountStatus;
import lombok.Getter;

/**
 * @author pc
 **/

@Getter
public class AccountStatusUpdatedEvent {

    private String accountId;
    private AccountStatus status;

    @JsonCreator
    public AccountStatusUpdatedEvent(
            @JsonProperty("accountId") String accountId,
            @JsonProperty("status") AccountStatus status
            ) {
        this.accountId = accountId;
        this.status = status;

    }
}