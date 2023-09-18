package com.ec.banking.account.cmd.infrastructure;

import com.ec.banking.cqrs.core.commands.BaseCommand;
import com.ec.banking.cqrs.core.commands.CommandHandlerMethod;
import com.ec.banking.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */

@Service
public class AccountCommandDispatcher implements CommandDispatcher {
    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        var handlers = routes.get(command.getClass());
        if(handlers == null || handlers.size() == 0){
            throw new RuntimeException("El command handler no fue registrado");
        }

        if(handlers.size() > 1){
            throw new RuntimeException("No puede enviar un command que tiene mas de un handler");
        }

        handlers.get(0).handle(command);
    }
}