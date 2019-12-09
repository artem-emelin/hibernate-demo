package com.dataart.hibernate.demo.controller;

import com.dataart.hibernate.demo.model.AccountModel;
import com.dataart.hibernate.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public AccountModel getAccount(@PathVariable("accountId") long accountId) {
        return accountService.getAccount(accountId);
    }
}
