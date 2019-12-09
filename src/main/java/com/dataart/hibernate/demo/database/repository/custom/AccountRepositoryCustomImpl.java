package com.dataart.hibernate.demo.database.repository.custom;

import com.dataart.hibernate.demo.database.entities.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
@PersistenceContext
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountRepositoryCustomImpl implements AccountRepositoryCustom {

    private final EntityManager em;

    @Override
    public Account findById(Long id) {
        return em.find(Account.class, id);
    }
}
