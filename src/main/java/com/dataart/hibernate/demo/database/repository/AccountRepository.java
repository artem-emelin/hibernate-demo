package com.dataart.hibernate.demo.database.repository;

import com.dataart.hibernate.demo.database.entities.Account;
import com.dataart.hibernate.demo.database.repository.custom.AccountRepositoryCustom;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountRepository extends Repository<Account, Long>, AccountRepositoryCustom {

}
