package com.dataart.hibernate.demo.service;

import com.dataart.hibernate.demo.database.repository.AccountRepository;
import com.dataart.hibernate.demo.database.entities.Account;
import com.dataart.hibernate.demo.model.AccountModel;
import com.dataart.hibernate.demo.util.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public AccountModel getAccount(long accountId) {
        LOG.info(">>>>>>>>>> Loading Account by Id {}", accountId);
        Account account = accountRepository.findById(accountId);
        return accountMapper.toAccountModel(account);
    }
}
