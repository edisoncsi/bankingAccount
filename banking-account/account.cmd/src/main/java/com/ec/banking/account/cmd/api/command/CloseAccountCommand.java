package com.ec.banking.account.cmd.api.command;

import com.ec.banking.cqrs.core.commands.BaseCommand;


public class CloseAccountCommand extends BaseCommand {
    public CloseAccountCommand(String id){
        super(id);
    }
}
