package com.ec.banking.cqrs.core.infrastructure;

import com.ec.banking.cqrs.core.domain.BaseEntity;
import com.ec.banking.cqrs.core.queries.BaseQuery;
import com.ec.banking.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

/**
 * @author edisoncsi on 18/9/23
 * @project banking-account
 */
public interface QueryDispatcher {

    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
