package com.ec.banking.cqrs.core.infrastructure;

import com.ec.banking.cqrs.core.commands.BaseCommand;
import com.ec.banking.cqrs.core.commands.CommandHandlerMethod;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */
public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
