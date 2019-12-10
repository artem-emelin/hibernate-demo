package com.dataart.hibernate.demo.config;

import com.dataart.hibernate.demo.util.sqltracker.SqlCountDatasource;
import com.p6spy.engine.spy.P6DataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Profile("dev")
@Component
public class DataSourceBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            return new P6DataSource((DataSource) bean);
        }
        return bean;
    }
}
