package com.yassine.cqrseventsourcingaxon.query.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author pc
 **/
@Getter @AllArgsConstructor
public class GetAccountStatementQuery {
    private String accountId;
}
