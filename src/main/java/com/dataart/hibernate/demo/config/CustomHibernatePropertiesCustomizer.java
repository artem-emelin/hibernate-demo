package com.dataart.hibernate.demo.config;

import com.dataart.hibernate.demo.model.OrderModel;
import com.dataart.hibernate.demo.util.ClassImportIntegrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class CustomHibernatePropertiesCustomizer implements HibernatePropertiesCustomizer {

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.integrator_provider",
                (IntegratorProvider) () -> Collections.singletonList(
                        new ClassImportIntegrator(List.of(OrderModel.class))
                )
        );
    }
}
