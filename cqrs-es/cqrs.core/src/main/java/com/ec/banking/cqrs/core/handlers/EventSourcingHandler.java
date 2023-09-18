package com.ec.banking.cqrs.core.handlers;

import com.ec.banking.cqrs.core.domain.AggregateRoot;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */
public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);
    T getById(String id);
}
