package com.mycompany.product.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

/**
 *
 * @author Rafael Cechinel Silvestri
 */
@Named
//@Component
public class HelloSpeaker {
    
    public String sayHello() {
        return "Hello World.";
    }
    
    @PostConstruct
    public void init() {
        System.out.println("post construct HelloService");
    }
    
    @PreDestroy
    public void destroy() {
        System.out.println("PreDestroy HelloService");
    }
    
}
