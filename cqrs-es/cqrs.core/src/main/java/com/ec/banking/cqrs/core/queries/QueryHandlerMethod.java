package com.ec.banking.cqrs.core.queries;

import com.ec.banking.cqrs.core.domain.BaseEntity;

import java.util.List;

/**
 * @author edisoncsi on 18/9/23
 * @project banking-account
 */

@FunctionalInterface
public interface QueryHandlerMethod <T extends BaseQuery>{
    List <BaseEntity> handle(T query);
}
