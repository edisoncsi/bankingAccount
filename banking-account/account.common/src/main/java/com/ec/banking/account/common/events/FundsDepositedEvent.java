package com.ec.banking.account.common.events;

import com.ec.banking.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FundsDepositedEvent extends BaseEvent {
    private double amount;
}
