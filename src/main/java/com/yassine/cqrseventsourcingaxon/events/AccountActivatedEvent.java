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
public class AccountActivatedEvent {

    private String accountId;
    private AccountStatus status;

    @JsonCreator
    public AccountActivatedEvent(
            @JsonProperty("accountId") String accountId,
            @JsonProperty("status") AccountStatus status) {
        this.accountId = accountId;
        this.status = status;
    }
}