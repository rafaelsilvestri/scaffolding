package com.mycompany.product.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
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
@ComponentScan(basePackages = {"com.mycompany.product"})
@Import({
    PersistenceConfig.class
})
public class SpringContextConfig {

    @Inject
    private Environment env;

    @PostConstruct
    public void init() {
        System.out.println("\n\n\nInitializing SpringContextConfig");
        //System.out.println("profile: " + env.getActiveProfiles());
    }

    @PreDestroy
    public void destroy() {
    }

}
