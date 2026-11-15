package com.yassine.cqrseventsourcingaxon.events;

import com.yassine.cqrseventsourcingaxon.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author pc
 **/

@Getter @AllArgsConstructor
public class AccountCreatedEvent {
    private String accountId;
    private double initialBalance;
    private AccountStatus status;
    private String currency;
}
