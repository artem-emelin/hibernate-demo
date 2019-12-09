package com.dataart.hibernate.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.util.Properties;

public class DatabaseConfigCustom {

    private static final String PREPARED_STATEMENT_CACHE_QUERIES_DEFAULT = "256";
    private static final String PREPARED_STATEMENT_CACHE_SIZE_MiB_DEFAULT = "5";
    private static final String PREPARE_THRESHOLD_DEFAULT = "5";

    private static final int ASYNC_DB_THREAD_QUEUE_CAPACITY = 1000;
    private static final int ASYNC_DB_THREAD_CORE_POOL_SIZE = 3;
    private static final long ASYNC_DB_THREAD_KEEP_ALIVE_TIME = 5L;

    @Autowired
    private Environment env;

    @Bean(name = "casinoDataSource", destroyMethod = "close")
    public HikariDataSource dataSource() {
        HikariConfig config = new HikariConfig();

        config.setDriverClassName(env.getProperty("jdbc.driverClass"));
        config.setJdbcUrl(env.getProperty("jdbc.url"));
        config.setUsername(env.getProperty("jdbc.username"));
        config.setPassword(env.getProperty("jdbc.password"));

        config.setRegisterMbeans(true);
        config.setPoolName("casino");

        config.setMaximumPoolSize(Integer.parseInt(env.getRequiredProperty("jdbc.pool.size.max")));
        config.setMinimumIdle(Integer.parseInt(env.getRequiredProperty("jdbc.pool.size.min")));
        config.setValidationTimeout(env.getRequiredProperty("jdbc.pool.connection.eviction.interval", Long.class));

        config.addDataSourceProperty("preparedStatementCacheQueries", 256);
        config.addDataSourceProperty("preparedStatementCacheSizeMiB", 5);
        config.addDataSourceProperty("prepareThreshold", 5);

        return new HikariDataSource(config);
    }

    @Bean(name = "casinoSessionFactory")
    @Autowired
    public SessionFactory sessionFactory(@Qualifier("casinoDataSource") DataSource dataSource) {
        return new LocalSessionFactoryBuilder(dataSource)
                .scanPackages("com.casino.project.database.entities")
                .addProperties(hibernateProperties())
                .buildSessionFactory();
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, env.getProperty("hibernate.id.new_generator_mappings"));

        hibernateProperties.setProperty(AvailableSettings.DIALECT, env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty(AvailableSettings.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty(AvailableSettings.FORMAT_SQL, env.getProperty("hibernate.format_sql"));
        hibernateProperties.setProperty(AvailableSettings.USE_SQL_COMMENTS, env.getProperty("hibernate.use_sql_comments"));

        hibernateProperties.setProperty(AvailableSettings.STATEMENT_FETCH_SIZE, env.getProperty("hibernate.jdbc.fetch_size"));
        hibernateProperties.setProperty(AvailableSettings.STATEMENT_BATCH_SIZE, env.getProperty("hibernate.jdbc.batch_size"));
        hibernateProperties.setProperty(AvailableSettings.BATCH_VERSIONED_DATA, env.getProperty("hibernate.jdbc.batch_versioned_data"));
        hibernateProperties.setProperty(AvailableSettings.ORDER_INSERTS, env.getProperty("hibernate.order_inserts"));
        hibernateProperties.setProperty(AvailableSettings.ORDER_UPDATES, env.getProperty("hibernate.order_updates"));

        return hibernateProperties;
    }
}
