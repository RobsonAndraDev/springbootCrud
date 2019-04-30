package com.robsonandradev.config;


import com.google.common.base.Preconditions;
import com.robsonandradev.repositories.dao.UserRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan({ "com.robsonandradev.repositories.dao", "com.robsonandradev.services" })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.robsonandradev.repositories.dao" }, repositoryBaseClass = UserRepositoryImp.class)
@EnableJpaAuditing
//@PropertySource("classpath:persistence.properties")
@Profile("!tc")
public class PersistenceConfiguration {

  @Autowired
  private Environment env;

  public PersistenceConfiguration() {
    super();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    emf.setDataSource(dataSource());
    emf.setPackagesToScan("com.robsonandradev.entities");

    final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    emf.setJpaVendorAdapter(vendorAdapter);
//    emf.setJpaProperties(hibernateProperties());

    return emf;
  }

  @Bean
  public DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("spring.datasource.driverClassName")));
    dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("spring.datasource.url")));
    dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("spring.datasource.username")));
    dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("spring.datasource.password")));

    return dataSource;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    final JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

}
