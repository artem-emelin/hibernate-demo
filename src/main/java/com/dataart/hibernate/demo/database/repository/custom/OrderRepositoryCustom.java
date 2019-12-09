package com.dataart.hibernate.demo.database.repository.custom;

import com.dataart.hibernate.demo.database.entities.Orders;
import com.dataart.hibernate.demo.model.OrderModel;

import java.util.List;

public interface OrderRepositoryCustom {

    Orders findByIdReadOnly(long id);

    void flushAndClear();

    List<OrderModel> getAllOrdersByProjectionTuple();

    List<OrderModel> getAllOrdersByProjectionConstructor();

    List<OrderModel> getAllOrdersByResultSetMapping();

    List<OrderModel> getViaHibernate();
}
