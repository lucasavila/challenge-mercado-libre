package com.mercado.libre.magneto.configuracion;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.mercado.libre.magneto.repositories")
@EnableConfigurationProperties(HibernateProperties.class)
public class HibernateConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public Properties hibernateProperties() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.afterPropertiesSet();
        Properties props = factoryBean.getObject();
        props.setProperty("hibernate.hbm2ddl.auto", hibernateProperties.getHbm2ddlAuto());
        props.setProperty("spring.jpa.hibernate.ddl-auto", hibernateProperties.getHbm2ddlAuto());
        return props;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setShowSql(hibernateProperties.isShowSql());
        return jpaVendorAdapter;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter, Properties hibernateProperties) {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource);
        entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManager.setPackagesToScan("com.mercado.libre.magneto.dominio");
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setJpaProperties(hibernateProperties);
        entityManager.afterPropertiesSet();
        return entityManager.getObject();
    }

    public HibernateProperties getHibernateProperties() {
        return hibernateProperties;
    }

    public void setHibernateProperties(HibernateProperties hibernateProperties) {
        this.hibernateProperties = hibernateProperties;
    }

    @Autowired
    private HibernateProperties hibernateProperties;
}
