package com.mycompany.customer.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * Configuration of the business, persistence and security layers.
 *
 * @author Rafael Cechinel Silvestri
 */
@Configuration
@ComponentScan(basePackages = {"com.mycompany.customer"})
@Import({
    PersistenceConfig.class
})
public class SpringContextConfig {

    private static final Logger LOGGER = Logger.getLogger(SpringContextConfig.class);

    @Inject
    private Environment env;

    @PostConstruct
    public void init() {
        LOGGER.info("Initializing " + getClass().getName());
    }

    @PreDestroy
    public void destroy() {
    }

}
