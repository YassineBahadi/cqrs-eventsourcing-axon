package com.yassine.cqrseventsourcingaxon.commands.controllers;

import com.yassine.cqrseventsourcingaxon.commands.commands.AddAccountCommand;
import com.yassine.cqrseventsourcingaxon.commands.commands.CreditAccountCommand;
import com.yassine.cqrseventsourcingaxon.commands.commands.DebitAccountCommand;
import com.yassine.cqrseventsourcingaxon.commands.commands.UpdateAccountStatusCommand;
import com.yassine.cqrseventsourcingaxon.commands.dtos.AddNewAccountRequestDTO;
import com.yassine.cqrseventsourcingaxon.commands.dtos.CreditAccountRequestDTO;
import com.yassine.cqrseventsourcingaxon.commands.dtos.DebitAccountRequestDTO;
import com.yassine.cqrseventsourcingaxon.commands.dtos.UpdateAccountStatusRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * @author pc
 **/
@RestController
@RequestMapping("/commands/accounts")
public class AccountCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    public AccountCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }

    @PostMapping("/add")
    public CompletableFuture<String> addNewAccount(@RequestBody AddNewAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new AddAccountCommand(
                UUID.randomUUID().toString(),
                request.initialBalance(),
                request.currency()
        ));

        return response;
    }

    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new CreditAccountCommand(
                request.accountId(),
                request.amount(),
                request.currency()
        ));

        return response;
    }

    @PostMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new DebitAccountCommand(
                request.accountId(),
                request.amount(),
                request.currency()
        ));

        return response;
    }

    @PutMapping("/updateStatus")
    public CompletableFuture<String> updateStatus(@RequestBody UpdateAccountStatusRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new UpdateAccountStatusCommand(
                request.accountId(),
                request.status()
        ));

        return response;
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception exception){
        return exception.getMessage();
    }

    @GetMapping("/events/{accountId}")
    public Stream eventStore(@PathVariable String accountId) {
        return eventStore.readEvents(accountId).asStream();
    }
}
