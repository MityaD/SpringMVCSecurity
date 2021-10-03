package com.yakut.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration                                                           //конфигурация
@EnableTransactionManagement                                             //включить управление транзакциями
@EnableJpaRepositories("com.yakut.spring.repository")                    //включить Java Persistence API хранилище
@ComponentScan("com.yakut.spring.service")                               //сканирование компанентов
public class JPAConfig {

    @Bean
            //локальный контейнер сущностей менеджер фабрики бинов
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        //устанавливаем источник данных
        emf.setDataSource(dataSource());
        //устанавливаем пакет для сканирования
        emf.setPackagesToScan("com.yakut.spring.model");
        //адаптер поставщика JPA
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //устанавливаем адаптер
        emf.setJpaVendorAdapter(vendorAdapter);
        //устанавливаем характеристики
        emf.setJpaProperties(additionalProperties());
        return emf;
    }

            //дополнительные характеристики
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return properties;
    }

    @Bean
           //источник данных
    public DataSource dataSource() {
        //источник данных диспетчера драйверов
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/yakushik_db");
        dataSource.setUsername( "yakushik" );
        dataSource.setPassword( "yakushik" );
        return dataSource;
    }

    @Bean
            //менеджер транзакций платформы
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
            //постпроцессор перевода исключений сохраняемости
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
