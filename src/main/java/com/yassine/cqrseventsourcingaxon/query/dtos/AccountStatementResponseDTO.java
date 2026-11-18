package com.yassine.cqrseventsourcingaxon.query.dtos;

import com.yassine.cqrseventsourcingaxon.query.entities.Account;
import com.yassine.cqrseventsourcingaxon.query.entities.AccountOperation;

import java.util.List;

/**
 * @author pc
 **/
public record AccountStatementResponseDTO(
        Account account, List<AccountOperation> operations
        ) {
}
