package com.dataart.hibernate.demo.controller;

import com.dataart.hibernate.demo.model.OrderModel;
import com.dataart.hibernate.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PutMapping("/batch-creation")
    public void updatePerson() {
        orderService.createBatch();
    }

    @GetMapping("/all-by-projection-constructor")
    public List<OrderModel> getAllOrdersByProjectionConstructor() {
        return orderService.getAllOrdersByProjectionConstructor();
    }

    @GetMapping("/all-by-projection-tuple")
    public List<OrderModel> getAllOrdersByProjectionTuple() {
        return orderService.getAllOrdersByProjectionTuple();
    }

    @GetMapping("/all-result-set-mapping")
    public List<OrderModel> getAllOrdersByResultSetMapping() {
        return orderService.getAllOrdersByResultSetMapping();
    }

    @GetMapping("/all-via-hibernate")
    public List<OrderModel> getViaHibernate() {
        return orderService.getViaHibernate();
    }
}
