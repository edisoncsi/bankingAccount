package com.ec.banking.account.cmd.infrastructure;

import com.ec.banking.account.cmd.domain.AccountAggregate;
import com.ec.banking.account.cmd.domain.EventStoreRepository;
import com.ec.banking.cqrs.core.events.BaseEvent;
import com.ec.banking.cqrs.core.events.EventModel;
import com.ec.banking.cqrs.core.exceptions.AggregateNotFoundException;
import com.ec.banking.cqrs.core.exceptions.ConcurrencyException;
import com.ec.banking.cqrs.core.infrastructure.EventStore;
import com.ec.banking.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */

@Service
public class AccountEventStore implements EventStore {

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if(expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion){
            throw new ConcurrencyException();
        }

        var version = expectedVersion;
        for(var event: events){
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel);
            if(!persistedEvent.getId().isEmpty()){
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }

    }

    @Override
    public List<BaseEvent> getEvent(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if(eventStream == null || eventStream.isEmpty()){
            throw  new AggregateNotFoundException("La cuenta del banco es incorrecta");
        }

        return eventStream.stream().map(EventModel::getEventData).collect(Collectors.toList());
    }
}