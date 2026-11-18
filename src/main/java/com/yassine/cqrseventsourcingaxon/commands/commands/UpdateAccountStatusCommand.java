package com.yassine.cqrseventsourcingaxon.commands.commands;

import com.yassine.cqrseventsourcingaxon.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author pc
 **/
@Getter @AllArgsConstructor
public class UpdateAccountStatusCommand {
    @TargetAggregateIdentifier
    private String id;
    private AccountStatus status;
}
