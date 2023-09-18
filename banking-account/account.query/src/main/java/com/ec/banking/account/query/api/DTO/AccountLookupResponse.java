package com.ec.banking.account.query.api.DTO;

import com.ec.banking.account.common.dto.BaseResponse;
import com.ec.banking.account.query.domain.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author edisoncsi on 18/9/23
 * @project banking-account
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountLookupResponse extends BaseResponse {
    private List<BankAccount> accounts;

    public AccountLookupResponse (String message){
        super(message);
    }
}
