package com.ec.banking.cqrs.core.events;

import com.ec.banking.cqrs.core.messages.Message;
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
public abstract class BaseEvent extends Message {
    private int version;
}