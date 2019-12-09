package com.dataart.hibernate.demo.util;

import com.dataart.hibernate.demo.model.AccountModel;
import com.dataart.hibernate.demo.database.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountModel toAccountModel(Account account);
}
