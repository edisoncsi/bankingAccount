package com.ec.banking.account.common.events;

import com.ec.banking.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class AccountClosedEvent extends BaseEvent {
}
