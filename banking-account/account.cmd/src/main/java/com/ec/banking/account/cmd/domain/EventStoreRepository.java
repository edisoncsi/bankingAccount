package com.ec.banking.account.cmd.domain;

import com.ec.banking.cqrs.core.events.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */
public interface EventStoreRepository extends MongoRepository<EventModel, String> {
    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}
