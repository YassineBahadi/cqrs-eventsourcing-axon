package com.yassine.cqrseventsourcingaxon.commands.dtos;

/**
 * @author pc
 **/
public record AddNewAccountRequestDTO(
        double initialBalance,
        String currency
) {
}
