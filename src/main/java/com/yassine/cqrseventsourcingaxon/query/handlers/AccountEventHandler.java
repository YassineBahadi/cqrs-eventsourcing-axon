package com.yassine.cqrseventsourcingaxon.query.handlers;

import com.yassine.cqrseventsourcingaxon.events.AccountCreatedEvent;
import com.yassine.cqrseventsourcingaxon.events.AccountCreditedEvent;
import com.yassine.cqrseventsourcingaxon.query.entities.Account;
import com.yassine.cqrseventsourcingaxon.query.repository.AccountRepository;
import com.yassine.cqrseventsourcingaxon.query.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.springframework.stereotype.Component;

/**
 * @author pc
 **/
@Component
@Slf4j
public class AccountEventHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    public AccountEventHandler(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event, EventMessage eventMessage ){
        log.info("============= Query Side AccountCreatedEvent Received ====================== ");
        Account account=Account.builder()
                .id(event.getAccountId())
                .balance(event.getInitialBalance())
                .status(event.getStatus())
                .createdAt(eventMessage.getTimestamp())
                .build();
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountCreditedEvent event){

    }
}
