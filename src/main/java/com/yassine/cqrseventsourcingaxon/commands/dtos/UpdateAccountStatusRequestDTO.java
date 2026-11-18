package com.yassine.cqrseventsourcingaxon.commands.dtos;

import com.yassine.cqrseventsourcingaxon.enums.AccountStatus;

/**
 * @author pc
 **/
public record UpdateAccountStatusRequestDTO(
        String accountId,
       AccountStatus status
) {
}
