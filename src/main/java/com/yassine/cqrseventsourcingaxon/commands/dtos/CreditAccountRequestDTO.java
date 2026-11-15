package com.yassine.cqrseventsourcingaxon.commands.dtos;

/**
 * @author pc
 **/
public record CreditAccountRequestDTO(
        String accountId,
        double amount,
        String currency
) {
}
