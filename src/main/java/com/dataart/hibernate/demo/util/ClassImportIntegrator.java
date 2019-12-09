package com.dataart.hibernate.demo.util;

import lombok.RequiredArgsConstructor;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ClassImportIntegrator implements Integrator {

    private final List<Class> classImportList;

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {

        Map<String, String> imports = metadata.getImports();
        for(Class<?> classImport : classImportList) {
            imports.put(classImport.getSimpleName(), classImport.getName());
        }
    }

    @Override
    public void disintegrate(
            SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {}
}
