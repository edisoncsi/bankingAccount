package com.ec.banking.account.cmd.infrastructure;

import com.ec.banking.account.cmd.domain.AccountAggregate;
import com.ec.banking.cqrs.core.domain.AggregateRoot;
import com.ec.banking.cqrs.core.events.BaseEvent;
import com.ec.banking.cqrs.core.handlers.EventSourcingHandler;
import com.ec.banking.cqrs.core.infrastructure.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */
@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {
    @Autowired
    private EventStore eventStore;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommitedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public AccountAggregate getById(String id) {
        var aggregate = new AccountAggregate();
        var events = eventStore.getEvent(id);
        if(events != null && !events.isEmpty()){
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }

        return aggregate;
    }
}