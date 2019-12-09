package com.dataart.hibernate.demo.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountModel {

    private long id;
    private BigDecimal amount;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
}
