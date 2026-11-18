package com.yassine.cqrseventsourcingaxon.commands.dtos;

/**
 * @author pc
 **/
public record DebitAccountRequestDTO(
        String accountId,
        double amount,
        String currency
) {
}
