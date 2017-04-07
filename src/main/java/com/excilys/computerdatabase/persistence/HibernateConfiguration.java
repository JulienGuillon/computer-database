package com.excilys.computerdatabase.persistence;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.excilys.computerdatabase.persistence"})
@ComponentScan(basePackages = "com.excilys.computerdatabase")
@PropertySource("classpath:database.properties")
public class HibernateConfiguration {

    @Value("${db.driver}")
    private String dataSourceClassName;
    
    @Value("${db.url}")
    private String jdbcUrl;
    
    @Value("${db.user}")
    private String username;
    
    @Value("${db.password}")
    private String password;
   
    @Bean 
    public Datasource dataSource() {
        Datasource dataSource = new Datasource();
        dataSource.setDriverClassName(dataSourceClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        return jpaVendorAdapter;
    }
    
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return properties;
    }

    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.excilys.computerdatabase");
        entityManagerFactoryBean.setJpaProperties(additionalProperties());
        return entityManagerFactoryBean;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }  
}