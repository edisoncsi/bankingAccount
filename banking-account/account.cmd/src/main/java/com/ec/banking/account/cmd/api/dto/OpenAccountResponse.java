package com.ec.banking.account.cmd.api.dto;

import com.ec.banking.account.common.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author edisoncsi on 15/9/23
 * @project banking-account
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccountResponse extends BaseResponse {
    private String id;

    public OpenAccountResponse(String message, String id){
        super(message);
        this.id = id;
    }
}
