package com.ec.banking.account.query.api.queries;

import com.ec.banking.account.query.api.DTO.EqualityType;
import com.ec.banking.account.query.domain.AccountRepository;
import com.ec.banking.account.query.domain.BankAccount;
import com.ec.banking.cqrs.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author edisoncsi on 18/9/23
 * @project banking-account
 */

@Service
public class AccountQueryHandler implements QueryHandler{

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<BaseEntity> handle(FindAllAccountsQuery query) {
        Iterable<BankAccount> bankAccounts = accountRepository.findAll();
        List<BaseEntity> bankAccountList = new ArrayList<>();
        bankAccounts.forEach(bankAccountList::add);

        return bankAccountList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQuery query) {
        var bankAccount = accountRepository.findById(query.getId());
        if(bankAccount.isEmpty()) return null;

        List<BaseEntity> bankAccountList = new ArrayList<>();
        bankAccountList.add(bankAccount.get());

        return bankAccountList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery query) {
        var bankAccount = accountRepository.findByAccountHolder(query.getAccountHolder());
        if(bankAccount.isEmpty()) return null;

        List<BaseEntity> bankAccountList = new ArrayList<>();
        bankAccountList.add(bankAccount.get());

        return bankAccountList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountWithBalanceQuery query) {
        List<BaseEntity> bankAccountList  = query.getEqualityType()== EqualityType.GREATHER_THAN?
                accountRepository.findByBalanceGreaterThan(query.getBalance()):
                accountRepository.findByBalanceLessThan(query.getBalance());

        return bankAccountList;
    }
}
