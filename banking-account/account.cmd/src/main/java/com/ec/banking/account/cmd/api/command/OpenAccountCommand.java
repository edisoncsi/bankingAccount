package com.ec.banking.account.cmd.api.command;

import com.ec.banking.account.common.dto.AccountType;
import com.ec.banking.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
