package com.dataart.hibernate.demo.service;

import com.dataart.hibernate.demo.database.entities.Orders;
import com.dataart.hibernate.demo.database.repository.OrderRepository;
import com.dataart.hibernate.demo.model.OrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private static final int BATCH_SIZE = 30;

    private final OrderRepository orderRepository;

    @Transactional
    public void createBatch() {
        for (int ind = 1; ind < 70; ind++) {
            Orders order = createOrder("Order Batching #" + ind);
            orderRepository.save(order);

            if (ind % BATCH_SIZE == 0) {
                LOG.info(">>>>>>>>>> Orderы SAVED via BATCH");
                orderRepository.flushAndClear();
            }
        }
    }

    public List<OrderModel> getAllOrdersByProjectionConstructor() {
        return orderRepository.getAllOrdersByProjectionConstructor();
    }

    public List<OrderModel> getAllOrdersByProjectionTuple() {
        return orderRepository.getAllOrdersByProjectionTuple();
    }

    public List<OrderModel> getAllOrdersByResultSetMapping() {
        return orderRepository.getAllOrdersByResultSetMapping();
    }

    public List<OrderModel> getViaHibernate() {
        return orderRepository.getViaHibernate();
    }

    private Orders createOrder(String orderName) {
        return Orders.builder()
                .orderName(orderName)
                .build();
    }
}
