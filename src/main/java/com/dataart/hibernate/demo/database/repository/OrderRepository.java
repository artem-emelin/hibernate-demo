package com.dataart.hibernate.demo.database.repository;

import com.dataart.hibernate.demo.database.entities.Orders;
import com.dataart.hibernate.demo.database.repository.custom.OrderRepositoryCustom;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface OrderRepository extends Repository<Orders, Long>, OrderRepositoryCustom {

    @Transactional
    Orders save(Orders order);

    Orders findById(long id);
}
