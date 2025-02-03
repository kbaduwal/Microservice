package com.khelna_jum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class KhelnaJumServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(KhelnaJumServiceRegistryApplication.class, args);
    }

}
