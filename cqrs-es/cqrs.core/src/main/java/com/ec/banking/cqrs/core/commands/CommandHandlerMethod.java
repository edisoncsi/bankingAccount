package com.ec.banking.cqrs.core.commands;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
    void handle(T command);
}
