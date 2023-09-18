package com.ec.banking.account.query.api.queries;

import com.ec.banking.cqrs.core.domain.BaseEntity;

import java.util.List;

/**
 * @author edisoncsi on 18/9/23
 * @project banking-account
 */
public interface QueryHandler {
    List<BaseEntity> handle(FindAllAccountsQuery query);
    List<BaseEntity> handle(FindAccountByIdQuery query);
    List<BaseEntity> handle(FindAccountByHolderQuery query);
    List<BaseEntity> handle(FindAccountWithBalanceQuery query);
}
