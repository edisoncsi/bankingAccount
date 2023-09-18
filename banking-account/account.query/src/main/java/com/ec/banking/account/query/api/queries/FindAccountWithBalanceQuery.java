package com.ec.banking.account.query.api.queries;

import com.ec.banking.account.query.api.DTO.EqualityType;
import com.ec.banking.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author edisoncsi on 18/9/23
 * @project banking-account
 */

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
    private double balance;
    private EqualityType equalityType;
}
