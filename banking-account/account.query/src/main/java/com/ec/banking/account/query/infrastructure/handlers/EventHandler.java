package com.ec.banking.account.query.infrastructure.handlers;

import com.ec.banking.account.common.events.AccountClosedEvent;
import com.ec.banking.account.common.events.AccountOpenedEvent;
import com.ec.banking.account.common.events.FundsDepositedEvent;
import com.ec.banking.account.common.events.FundsWithdrawnEvent;

/**
 * @author edisoncsi on 15/9/23
 * @project banking-account
 */
public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
