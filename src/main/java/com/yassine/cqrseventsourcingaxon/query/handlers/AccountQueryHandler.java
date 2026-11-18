package com.yassine.cqrseventsourcingaxon.query.handlers;

import com.yassine.cqrseventsourcingaxon.query.dtos.AccountStatementResponseDTO;
import com.yassine.cqrseventsourcingaxon.query.entities.Account;
import com.yassine.cqrseventsourcingaxon.query.entities.AccountOperation;
import com.yassine.cqrseventsourcingaxon.query.queries.GetAccountStatementQuery;
import com.yassine.cqrseventsourcingaxon.query.queries.GetAllAccountsQuery;
import com.yassine.cqrseventsourcingaxon.query.repository.AccountRepository;
import com.yassine.cqrseventsourcingaxon.query.repository.OperationRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author pc
 **/
@Component
public class AccountQueryHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;


    public AccountQueryHandler(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        return accountRepository.findAll();
    }

    @QueryHandler
    public AccountStatementResponseDTO on(GetAccountStatementQuery query){
        Account account=accountRepository.findById(query.getAccountId()).get();
        List<AccountOperation> operations=operationRepository.findByAccountId(query.getAccountId());
        return new AccountStatementResponseDTO(account,operations);
    }


}
