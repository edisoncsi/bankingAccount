package com.ec.banking.account.query.infrastructure;

import com.ec.banking.cqrs.core.domain.BaseEntity;
import com.ec.banking.cqrs.core.infrastructure.QueryDispatcher;
import com.ec.banking.cqrs.core.queries.BaseQuery;
import com.ec.banking.cqrs.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author edisoncsi on 18/9/23
 * @project banking-account
 */

@Service
public class AccountQueryDispatcher implements QueryDispatcher {

    private final Map <Class<? extends BaseQuery>, List<QueryHandlerMethod>>routes= new HashMap<>();
    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        var handlers = routes.get(query.getClass());
        if(handlers == null || handlers.size() == 0){
            throw new RuntimeException("El query handler no fue registrado");
        }

        if(handlers.size() > 1){
            throw new RuntimeException("No puede enviar un query que tiene mas de un handler");
        }

        return handlers.get(0).handle(query);
    }
}
