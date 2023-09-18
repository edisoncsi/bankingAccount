package com.ec.banking.account.cmd.api.command;

import com.ec.banking.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class WithdrawFundsCommand extends BaseCommand {
    private double amount;
}
