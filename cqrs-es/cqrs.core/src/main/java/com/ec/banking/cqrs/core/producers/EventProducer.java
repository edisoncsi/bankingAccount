package com.ec.banking.cqrs.core.producers;

import com.ec.banking.cqrs.core.events.BaseEvent;

/**
 * @author edisoncsi on 15/9/23
 * @project banking-account
 */
public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
