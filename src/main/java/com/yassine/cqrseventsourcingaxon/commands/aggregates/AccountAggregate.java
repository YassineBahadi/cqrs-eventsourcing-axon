package com.yassine.cqrseventsourcingaxon.commands.aggregates;

import com.yassine.cqrseventsourcingaxon.commands.commands.AddAccountCommand;
import com.yassine.cqrseventsourcingaxon.commands.commands.CreditAccountCommand;
import com.yassine.cqrseventsourcingaxon.commands.commands.DebitAccountCommand;
import com.yassine.cqrseventsourcingaxon.enums.AccountStatus;
import com.yassine.cqrseventsourcingaxon.events.AccountActivatedEvent;
import com.yassine.cqrseventsourcingaxon.events.AccountCreatedEvent;
import com.yassine.cqrseventsourcingaxon.events.AccountCreditedEvent;
import com.yassine.cqrseventsourcingaxon.events.AccountDebitedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * @author pc
 **/
@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
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

        AggregateLifecycle.apply(new AccountActivatedEvent(
                command.getId(),
                AccountStatus.ACTIVATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("@@@@@@@@@@ AccountCreatedEvent Occured @@@@@@@@@@@@@");
        this.accountId=event.getAccountId();
        this.balance=event.getInitialBalance();
        this.status=event.getStatus();
    }


    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        log.info("@@@@@@@@@@ AccountActivatedEvent Occured @@@@@@@@@@@@@");
        this.accountId=event.getAccountId();
        this.status=event.getStatus();
    }




    @CommandHandler
    public void handle(CreditAccountCommand command){
        log.info("@@@@@@@@@@ CreditAccountCommand Received @@@@@@@@@@@@@");
        if(!status.equals(AccountStatus.ACTIVATED)){
            throw new RuntimeException("The account "+command.getId()+" is not activated");
        }
        if(command.getAmount()<=0) throw new IllegalArgumentException("Amount must be positive");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }


    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        log.info("@@@@@@@@@@ AccountCreditedEvent Occured @@@@@@@@@@@@@");
        this.accountId=event.getAccountId();
        this.balance= this.balance+  event.getAmount();
    }

    @CommandHandler
    public void handle(DebitAccountCommand command){
        log.info("@@@@@@@@@@ DebitAccountCommand Received @@@@@@@@@@@@@");
        if(!status.equals(AccountStatus.ACTIVATED)){
            throw new RuntimeException("The account "+command.getId()+" is not activated");
        }
        if(balance<command.getAmount()) throw new RuntimeException("Balance not sufficient");
        if(command.getAmount()<=0) throw new IllegalArgumentException("Amount must be positive");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }


    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        log.info("@@@@@@@@@@ AccountDebitedEvent Occured @@@@@@@@@@@@@");
        this.accountId=event.getAccountId();
        this.balance= this.balance -   event.getAmount();
    }



}
