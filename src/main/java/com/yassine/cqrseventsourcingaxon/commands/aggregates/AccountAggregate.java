package com.yassine.cqrseventsourcingaxon.commands.aggregates;

import com.yassine.cqrseventsourcingaxon.commands.commands.AddAccountCommand;
import com.yassine.cqrseventsourcingaxon.enums.AccountStatus;
import com.yassine.cqrseventsourcingaxon.events.AccountCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * @author pc
 **/
@Aggregate
@Slf4j
public class AccountAggregate {
    private String accountId;
    private double balance;
    private AccountStatus status;

    public AccountAggregate(){}

    @CommandHandler
    public AccountAggregate(AddAccountCommand command){
        log.info("@@@@@@@@@@ AddAccountCommand Received @@@@@@@@@@@@@");
        if(command.getInitialBalance()<=0) throw new IllegalArgumentException("Balance must be positive");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                AccountStatus.CREATED,
                command.getCurrency()
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("@@@@@@@@@@ AccountCreatedEvent Occured @@@@@@@@@@@@@");
        this.accountId=event.getAccountId();
        this.balance=event.getInitialBalance();
        this.status=event.getStatus();
    }
}
