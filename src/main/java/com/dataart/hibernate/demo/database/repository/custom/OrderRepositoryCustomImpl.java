package com.dataart.hibernate.demo.database.repository.custom;

import com.dataart.hibernate.demo.database.entities.Orders;
import com.dataart.hibernate.demo.model.OrderModel;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.jdbc.AbstractWork;
import org.hibernate.jdbc.Work;
import org.hibernate.jpa.QueryHints;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Repository
@PersistenceContext
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final EntityManager em;

    public Orders findByIdReadOnly(long orderId) {
        return em.createQuery("FROM Orders ord WHERE ord.id = :orderId", Orders.class)
                .setParameter("orderId", orderId)
                .setHint(QueryHints.HINT_READONLY, true)
                .getSingleResult();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void flushAndClear() {
        em.flush();
        em.clear();
    }

    @Override
    public List<OrderModel> getAllOrdersByProjectionTuple() {
        // !!!!! Just like with ScrollableResults, you should always close a Hibernate Stream
        // !!!!! either explicitly or using a try-with-resources block.

        try(Stream<Tuple> stream = em.createQuery("select o.id as id, o.orderName as orderName from Orders o", Tuple.class)
                .getResultStream()) {
            return stream
                    .map(tuple -> new OrderModel(tuple.get("id", Long.class), tuple.get("orderName", String.class)))
                    .collect(toList());
        }
    }

    @Override
    public List<OrderModel> getAllOrdersByProjectionConstructor() {
        return em.createQuery(
                "select new com.dataart.hibernate.demo.model.OrderModel(o.id, o.orderName) " +
                        "from Orders o", OrderModel.class)
                .getResultList();
    }

    @Override
    public List<OrderModel> getAllOrdersByResultSetMapping() {
        return em.createNamedQuery(Orders.GET_ALL_ORDERS, OrderModel.class).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderModel> getViaHibernate() {
        return em.createNativeQuery("select o.id as \"id\", o.order_name as \"orderName\" from orders o")
                .unwrap(org.hibernate.query.NativeQuery.class)
                .addScalar("id", LongType.INSTANCE)
                .addScalar("orderName", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(OrderModel.class))
                .getResultList();
    }

    public void jdbcWork() {
        em.unwrap(Session.class).doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                // JDBC connection
            }
        });
    }
}
