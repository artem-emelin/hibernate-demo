package com.dataart.hibernate.demo.database.repository.custom;

import com.dataart.hibernate.demo.database.entities.Account;

public interface AccountRepositoryCustom {

    Account findById(Long id);
}
