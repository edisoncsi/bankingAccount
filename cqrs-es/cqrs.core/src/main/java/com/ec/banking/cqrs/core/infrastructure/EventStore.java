package com.ec.banking.cqrs.core.infrastructure;

import com.ec.banking.cqrs.core.events.BaseEvent;

import java.util.List;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */
public interface EventStore {
    void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvent(String aggregateId);
}
