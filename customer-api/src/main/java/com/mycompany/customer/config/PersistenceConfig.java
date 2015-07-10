package com.mycompany.customer.config;

import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Spring JPA Data configuration.
 *
 * @author Rafael Cechinel Silvestri
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.mycompany.customer.dao"})
//@EnableTransactionManagement
@PropertySource({"classpath:persistence-${spring.profiles.active:ci}.properties"})
public class PersistenceConfig extends JpaRepositoryConfigExtension {

    private static final Logger LOGGER = Logger.getLogger(PersistenceConfig.class);

    @Inject
    private Environment env;

    @PostConstruct
    public void init() {
        LOGGER.info("Initializing " + getClass().getName());

//      org.apache.catalina.loader.StandardClassLoader@7eda2dbb
//	excludeUnlistedClasses: true
//	JTA datasource: null
//	Non JTA datasource: org.springframework.jdbc.datasource.DriverManagerDataSource@1018dd15
//	Transaction type: RESOURCE_LOCAL
//	PU root URL: file:/D:/dev/rep/seed/customer-api/target/customer-api-1.0-SNAPSHOT/WEB-INF/classes/
//	Shared Cache Mode: UNSPECIFIED
//	Validation Mode: AUTO
//	Jar files URLs []
//	Managed classes names [
//		com.mycompany.customer.model.Address
//		com.mycompany.customer.model.Customer]
//	Mapping files names []
//	Properties []
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.mycompany.customer.model"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        additionalProperties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        em.setJpaProperties(additionalProperties);

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
