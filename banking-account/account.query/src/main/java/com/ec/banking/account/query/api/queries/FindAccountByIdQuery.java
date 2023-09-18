package com.ec.banking.account.query.api.queries;

import com.ec.banking.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author edisoncsi on 18/9/23
 * @project banking-account
 */

@Data
@AllArgsConstructor
public class FindAccountByIdQuery extends BaseQuery {
    private String id;
}
