package com.ec.banking.account.cmd.domain;

import com.ec.banking.account.cmd.api.command.OpenAccountCommand;
import com.ec.banking.account.common.events.AccountClosedEvent;
import com.ec.banking.account.common.events.AccountOpenedEvent;
import com.ec.banking.account.common.events.FundsDepositedEvent;
import com.ec.banking.account.common.events.FundsWithdrawnEvent;
import com.ec.banking.cqrs.core.domain.AggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private Boolean active;
    @Getter
    private double balance;

    public AccountAggregate(OpenAccountCommand command){
        raiseEvent(AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolder())
                .createdDate(new Date())
                .accountType(command.getAccountType())
                .openingBalance(command.getOpeningBalance())
                .build());
    }

    public void apply(AccountOpenedEvent event){
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount){
        if(!this.active){
            throw new IllegalStateException("Los fondos no pueden ser depositados en esta cuenta");
        }

        if(amount <= 0){
            throw new IllegalStateException("El deposito de dinero no puede ser cero menos que cero");
        }

        raiseEvent(FundsDepositedEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());

    }

    public void apply(FundsDepositedEvent event){
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFunds(double amount){
        if(!this.active){
            throw new IllegalStateException("La cuenta bancaria esta cerrada");
        }
        raiseEvent(FundsWithdrawnEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }

    public void apply(FundsWithdrawnEvent event){
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount(){
        if(!active){
            throw new IllegalStateException("La cuenta de banco esta cerrada");
        }

        raiseEvent(AccountClosedEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(AccountClosedEvent event){
        this.id = event.getId();
        this.active = false;
    }
}