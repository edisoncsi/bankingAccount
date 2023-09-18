package com.ec.banking.account.cmd.api.command;

/**
 * @author edisoncsi on 15/9/23
 * @project banking-account
 */
public interface CommandHandler {
    void handle(OpenAccountCommand command);
    void handle(DepositFundsCommand command);
    void handle(WithdrawFundsCommand command);
    void handle(CloseAccountCommand command);
}
